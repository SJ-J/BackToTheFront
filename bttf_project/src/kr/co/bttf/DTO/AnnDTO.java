package kr.co.bttf.DTO;

public class AnnDTO {

	private int post_id;
	private String post_subject;
	private String post_contents;
	private String writer;
	private int post_vcount;
	private String post_regdate;
	
	// get set 
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getPost_subject() {
		return post_subject;
	}
	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}
	public String getPost_contents() {
		return post_contents;
	}
	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPost_vcount() {
		return post_vcount;
	}
	public void setPost_vcount(int post_vcount) {
		this.post_vcount = post_vcount;
	}
	public String getPost_regdate() {
		return post_regdate;
	}
	public void setPost_regdate(String post_regdate) {
		this.post_regdate = post_regdate;
	}
}
