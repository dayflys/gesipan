package board.model.DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public interface DAOinterface<T> {
	public List<T> list(String id);
	public boolean insert(T t);
	public List<T> search(String name,String password);
	public boolean delete(int id, String name);
	public boolean update(T vo);
	public void close(Statement s, ResultSet r);
}
