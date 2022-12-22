package board.model.DAO.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbcdb?characterEncoding=UTF-8& serverTimezone=UTC"; 
			String user = "root";
			String passwd = "sug223344!";
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			System.out.println("DB접속오류 발생!!");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
