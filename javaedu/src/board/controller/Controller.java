package board.controller;

public interface Controller {
	public void Inserting(String name,String title, String content);
	public void Listing(String id);
	public void Searching(String name,String password);
	public void Updating(int id, String content);
	public void Deleting(int id,String name);
}
