package com.javaex.ex05;

import java.sql.Date;

public class BookVo {
	//필드
	private int bookId;
	private String title;
	private String pubs;
	private Date pubDate;
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	//생성자
	public BookVo() {
	}
	
	public BookVo(String title, String pubs, String pubDate, int authorId) {
		super();
		this.title = title;
		this.pubs = pubs;
		this.pubDate = Date.valueOf(pubDate);
		this.authorId = authorId;
	}
	
	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = Date.valueOf(pubDate);
		this.authorId = authorId;
	}
	
	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId, String authorName,
			String authorDesc) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = Date.valueOf(pubDate);
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	//겟터셋터
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = Date.valueOf(pubDate);
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "[책 번호:" + bookId + "\t 제목:" + title + "\t 출판사:" + pubs + "\t 출판일:" + pubDate
				+ "\t 작가 번호:" + authorId + "\t 작가:" + authorName + "\t 작가설명:" + authorDesc + "]";
	}
	
	
}
