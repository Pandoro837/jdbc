package com.javaex.ex05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id ="webdb";
	private String pw ="webdb";
	
	//메소드 일반
	//DB 연결
	private void getConnection() {
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		 
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	//연결 종료
	private void close() {
		try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	//bookList 작성
	public List<BookVo> getBookList(){
	
		List<BookVo> bookList = new ArrayList<BookVo> ();
		
		getConnection();
		
		try {
			String query ="";
			query+=" SELECT ";
			query+=" 	bk.book_id, ";
			query+=" 	bk.title, ";
			query+=" 	bk.pubs, ";
			query+="	TO_CHAR(bk.pub_date, 'yyyy-mm-dd'), ";
			query+=" 	at.author_id, ";
			query+=" 	at.author_name, ";
			query+=" 	at.author_desc ";
			query+=" FROM ";
			query+=" 	book bk,";
			query+=" 	author at ";
			query+=" WHERE bk.author_id = at.author_id ";
			query+=" ORDER BY bk.book_id ASC ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("TO_CHAR(bk.pub_date,'yyyy-mm-dd')");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				BookVo bookVo = new BookVo(bookId, bookTitle, pubs, pubDate, authorId, authorName, authorDesc);
				bookList.add(bookVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return bookList;
	
	}
	
	//DB에 정보를 입력
	public int bookInsert(BookVo bookVo) {
		
		int iCount = -1;

		getConnection();
		
		try {
			String query ="";
			query+=" INSERT ";
			query+=" INTO ";
			query+=" 	book ";
			query+=" VALUES ";
			query+=" 	(seq_book_id.NEXTVAL , ?, ?, ?, ?) ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
		    pstmt.setString(2, bookVo.getPubs());
		    pstmt.setDate(3, bookVo.getPubDate());
		    pstmt.setInt(4, bookVo.getAuthorId());
			
		    //결과 처리
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}
	
	public int getUpdate(BookVo bookVo) {
		int iCount = -1;
		
		getConnection();
		
		try {
			String query ="";
			query+=" UPDATE ";
			query+=" book ";
			query+=" SET ";
			query+=" title = ?, ";
			query+=" pubs = ?, ";
			query+=" pub_date = ?, ";
			query+=" author_id = ? ";
			query+=" WHERE ";
			query+=" book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
		    pstmt.setString(2, bookVo.getPubs());
		    pstmt.setDate(3, bookVo.getPubDate());
		    pstmt.setInt(4, bookVo.getAuthorId());
		    pstmt.setInt(5, bookVo.getBookId());
			
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}

	public int delete(int bookId) {
		int iCount = -1;

		getConnection();
		
		try {
			String query ="";
			query+=" DELETE ";
			query+=" FROM ";
			query+=" 	book ";
			query+=" WHERE book_id = ? ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			//결과 처리
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}
	
	public List<BookVo> search(String searchWord){
		
		List<BookVo> bookList = new ArrayList<BookVo> ();
		
		getConnection();
		
		try {
			String query ="";
			query+=" SELECT ";
			query+=" 	bk.book_id, ";
			query+=" 	bk.title, ";
			query+=" 	bk.pubs, ";
			query+="	TO_CHAR(bk.pub_date, 'yyyy-mm-dd'), ";
			query+=" 	at.author_id, ";
			query+=" 	at.author_name, ";
			query+=" 	at.author_desc ";
			query+=" FROM ";
			query+=" 	book bk,";
			query+=" 	author at ";
			query+=" WHERE bk.author_id = at.author_id ";
			query+="   AND (bk.title || bk.pubs || at.author_name) LIKE ? ";
			query+=" ORDER BY bk.book_id ASC ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchWord);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("TO_CHAR(bk.pub_date,'yyyy-mm-dd')");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				BookVo bookVo = new BookVo(bookId, bookTitle, pubs, pubDate, authorId, authorName, authorDesc);
				bookList.add(bookVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return bookList;
	
	}
	

}
		
	
	

