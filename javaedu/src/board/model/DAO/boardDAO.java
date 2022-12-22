package board.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.model.DAO.Connection.CreateBoard;
import board.model.VO.boardVO;


public class boardDAO implements DAOinterface<boardVO>{
	Connection conn;
	public boardDAO(Connection conn){
		this.conn = conn;
		CreateBoard.Create(conn);
	}
	
	public List<boardVO> list(String name) {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<boardVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT id,name,title,date_format(curdate, '%Y년 %m월 %d일') AS curdate, contents FROM board");
			boardVO vo = null;
			while (rs.next()) {
				vo = new boardVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setCurdate(rs.getString("curdate"));
				vo.setContents(rs.getString("contents"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return list;
	}

	public boolean insert(boardVO vo) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO board(name,title,curdate,contents) VALUES(?,?,now(),?)");
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("board insert 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null);
		}
	}

	public List<boardVO> search(String name, String password) {
		PreparedStatement pstmt = null;
		boardVO vo = null;
		ResultSet rs = null;
		List<boardVO> list = new ArrayList<>();
		System.out.println(name);
		try {
			pstmt = conn.prepareStatement(
					"SELECT id,name, date_format(curdate, '%Y년 %m월 %d일') AS curdate, contents FROM board WHERE name = ?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new boardVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setCurdate(rs.getString("curdate"));
				vo.setContents(rs.getString("contents"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return list;
	}

	public boolean delete(int id,String name) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM board WHERE id = '" + id + "' and name = '" + name+"'");

			if (pstmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.err.println("delete 과정에서 오류 발생 " );
			return false;
		} finally {
			close(pstmt, null);
		}
	}
	
	public boolean update(boardVO vo) {	
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update board set contents = ? where id = ? "); 
			pstmt.setString(1, vo.getContents());
			pstmt.setInt(2,  vo.getId());
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			close(pstmt,null);
		}
		return result;
	}

	public void close(Statement s, ResultSet r) {
		try {
			if (r != null)
				r.close();
			if (s != null)
				s.close();
		} catch (SQLException e) {
			System.err.println("객체 close 과정에서 문제 발생" + e);
		}
	}

}
