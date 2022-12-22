package board.model.VO;

public class adminVO extends memberVO {
	private boolean admin;
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
	}

	@Override
	public String getCurdate() {
		// TODO Auto-generated method stub
		return super.getCurdate();
	}

	@Override
	public void setCurdate(String curdate) {
		// TODO Auto-generated method stub
		super.setCurdate(curdate);
	}

	@Override
	public String toString() {
		return super.toString()+"admi :" +this.isAdmin();
	}
	
}
