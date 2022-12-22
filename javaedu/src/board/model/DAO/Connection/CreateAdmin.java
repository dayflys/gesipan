package board.model.DAO.Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAdmin extends ConnectDB{


	public static void Create(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("statement 안 열림 ");
		}
		try {
			stmt.executeUpdate("CREATE TABLE admin"
					+ "(name varchar(15) ,"
					+" password varchar(30),"
					+ " admin boolean default 1 not null,"
					+ "foreign key (name) references member(name))");
			System.out.println("테이블 생성 완료");
		} catch(Exception e) {
//			System.out.println("테이블 이미 있음3" +e);
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("statement 안 닫힘 ");
		}
		
	}

}
