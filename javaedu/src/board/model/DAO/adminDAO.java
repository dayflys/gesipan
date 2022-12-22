package board.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.model.DAO.Connection.CreateAdmin;
import board.model.VO.adminVO;


public class adminDAO implements DAOinterface<adminVO>{
	private Connection conn;
	public adminDAO(Connection conn){
		this.conn = conn;
		CreateAdmin.Create(conn);
	}
	
	public List<adminVO> list() {

		Statement stmt = null;
		ResultSet rs = null;
		List<adminVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT name FROM admin");
			adminVO vo = null;
			while (rs.next()) {
				vo = new adminVO();
				vo.setName(rs.getString("name"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return list;
	}

	@Override
	public boolean insert(adminVO vo) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO admin(name,admin) VALUES(?,1)");
			pstmt.setString(1, vo.getName());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("admin insert 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null);
		}
	}

	@Override
	public List<adminVO> search(String name,String password) {
		PreparedStatement pstmt = null;
		adminVO vo = null;
		ResultSet rs = null;
		List<adminVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(
					"SELECT name FROM admin WHERE name = ? and password = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new adminVO();
				vo.setName(rs.getString("name"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return list;
	}

	@Override
	public boolean delete(int id, String name) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROm admin WHERE name = " + name);

			if (pstmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.err.println("delete 과정에서 오류 발생 ");
			return false;
		} finally {
			close(pstmt, null);
		}
	}

	@Override
	public boolean update(adminVO vo) {
		return false;
	}

	@Override
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

	@Override
	public List<adminVO> list(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
