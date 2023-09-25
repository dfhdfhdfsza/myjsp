package domain;

public class commentVO 
{
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String regdate;

	
	public commentVO() {}
	
	//post
	public commentVO(int bno, String writer, String content) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}

	//modify
	public commentVO(int cno, String content) {
		super();
		this.cno = cno;
		this.content = content;
	}

	//---------------------------------getter/setter------------------------------
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
