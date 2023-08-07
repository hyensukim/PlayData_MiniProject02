package models;

import java.sql.Date;

public class Guest {
	/**
	 * DO 
	 * - 기본키(번호)
	 * - 작성자
	 * - 비밀번호
	 * - 제목
	 * - 내용
	 * - 작석 일시
	 */
	
	private int gId;
	private String name;
	private String pass;
	private String title;
	private String content;
	private Date date;
	
	public Guest() {}
	
	public Guest(int gId, String name, String pass, String title, String content, Date date) {
		super();
		this.gId = gId;
		this.name = name;
		this.pass = pass;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Guest [gId=" + gId + ", name=" + name + ", pass=" + pass + ", title=" + title + ", content=" + content
				+ ", date=" + date + "]";
	}
	
}
