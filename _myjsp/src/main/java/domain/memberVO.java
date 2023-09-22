package domain;

public class memberVO 
{
	private String id;
	private String pw;
	private String regdate;
	private String last_login;
	
	
	public memberVO() {}
	
	//join
	public memberVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	//---------------------------getter/setter----------------------------------


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public String getLast_login() {
		return last_login;
	}


	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	
	
}
