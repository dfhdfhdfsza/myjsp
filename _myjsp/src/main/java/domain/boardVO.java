package domain;

public class boardVO 
{
	private int bno;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	private String category;
	private String moddate;
	
	public boardVO() {}
	
	
	//insert
	public boardVO(String title, String writer, String content) 
	{
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	

	//detail
	public boardVO(int bno, String title, String writer, String regdate, String content, String moddate) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
		this.moddate = moddate;
	}
	
	//edit
	public boardVO(int bno, String title, String content) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
	}


	//---------------------getter/setter------------------------------
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

	public String getModdate() {
		return moddate;
	}


	public void setModdate(String moddate) {
		this.moddate = moddate;
	}


	@Override
	public String toString() {
		return "boardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", regdate=" + regdate + ", content="
				+ content + ", category=" + category + "]";
	}
	

}
