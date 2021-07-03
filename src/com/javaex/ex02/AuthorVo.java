package com.javaex.ex02;

public class AuthorVo {

	//필드
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	//생성자
	public AuthorVo() {
		
	}
	
	public AuthorVo(int authorId, String authorName, String authorDesc) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	
	//메소드 겟터셋터
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
		return "작가번호: " + authorId + "\t작가이름: " + authorName + "\t작가설명: " + authorDesc;
	}
	
	
	
}
