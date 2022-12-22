package board.model.DAO.Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBoard extends ConnectDB{


	public static void Create(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("statement 안 열림 ");
		}
		try {
			stmt.executeUpdate("CREATE TABLE board"
					+ "(id int(8) auto_increment primary key,"
					+ "title varchar(20) not null,"
					+ " name varchar(15) not null,"
					+ " heart int(32) default 0,"
					+ " curdate date not null,"
					+ " contents varchar(300)"
					+ ")");
			System.out.println("테이블 생성 완료");
		} catch(Exception e) {
//			System.out.println("테이블 이미 있음2"+e);
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("statement 안 닫힘 ");
		}
		
	}
	
}
