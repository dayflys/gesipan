package board.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.model.DAO.Connection.CreateMember;
import board.model.VO.memberVO;

public class memberDAO implements DAOinterface<memberVO>{
	Connection conn;
	public memberDAO(Connection conn){
		this.conn = conn;
		CreateMember.Create(conn);
	}

	public List<memberVO> list(String name) {
		Statement stmt = null;
		ResultSet rs = null;
		List<memberVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT name, date_format(curdate, '%Y년 %m월 %d일') AS curdate FROM member where name = '"+name+"'");
			memberVO vo = null;
			while (rs.next()) {
				vo = new memberVO();
				vo.setName(rs.getString("name"));
				vo.setCurdate(rs.getString("curdate"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean insert(memberVO vo) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO member VALUES(?,?,now())");
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("member insert 과정에서 오류 발생 " );
			return false;
		}
	}
	
	public List<memberVO> search(String name, String password) {
		PreparedStatement pstmt = null;
		memberVO vo = null;
		ResultSet rs = null;
		List<memberVO> list = new ArrayList<>();
		try {
			if(password != null) {
				pstmt = conn.prepareStatement(
						"SELECT name,password, date_format(curdate, '%Y년 %m월 %d일') AS curdate FROM member WHERE name = ? and password = ?");
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
			}else {
				pstmt = conn.prepareStatement(
						"SELECT name,password, date_format(curdate, '%Y년 %m월 %d일') AS curdate FROM member WHERE name = ? ");
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				vo = new memberVO();
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setCurdate(rs.getString("curdate"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	public boolean delete(int id, String name) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM member WHERE name = '" + name+"'");

			if (pstmt.executeUpdate() != 0)
			{	System.out.println("회원 탈퇴가 완료되었습니다.");
				return true;
				}
			else
				return false;
		} catch (SQLException e) {
			System.err.println("delete 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null);
		}
	}

	public boolean update(memberVO vo) {	
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
						"update member set password = ? where name = ?"); 
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2,  vo.getName());
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
