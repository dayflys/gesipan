package board.model.VO;

public class memberVO {
	private String name;
	private String password;
	private String curdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCurdate() {
		return curdate;
	}
	public void setCurdate(String curdate) {
		this.curdate = curdate;
	}
	
	@Override
	public String toString() {
		return "memberVO [name=" + name + ", password=" + password + ", curdate=" + curdate + "]";
	}


}
