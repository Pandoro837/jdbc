package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id ="webdb";
	private String pw ="webdb";
	
	
	//생성자
	
	//메소드 겟터셋터
	
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
	
	//DB 정보를 arrayList에 넣어 전달
	public List<AuthorVo> getAuthorList() {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo> ();
		
		getConnection();
			
		try {
			String query = "";
			query+= " SELECT ";
			query+= " 		author_id, ";
			query+= " 		author_name, ";
			query+= " 		author_desc ";
			query+= " FROM ";
			query+= " 		author ";
			query+= " ORDER BY author_id ASC ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(query);
			
			// 4.결과처리
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(authorVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return authorList;
	}
	
	//DB에 정보를 입력
	public int authorInsert(String authorName, String authorDesc) {
		int iCount = -1; //어떤 과정에서 실패했는지 확인하기 위해 사용
		
		getConnection();
			
	    try {
	    	String query = "";
			query+=" INSERT ";
			query+=" INTO ";
			query+=" author ";
			query+=" VALUES ";
			query+=" (seq_author_id.NEXTVAL, ?, ?)";
	    	
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorName);
		    pstmt.setString(2, authorDesc);
			    
		    iCount = pstmt.executeUpdate();	//query문의 작동 실패 시 iCount = 0
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		close();
	    
		return iCount;
	}
	
	public int authorUpdate(int authorId, String authorName, String authorDesc) {
		int iCount = -1; //어떤 과정에서 실패했는지 확인하기 위해 사용
		
		getConnection();
	
		try {
			String query = "";
			query+=" UPDATE ";
			query+=" author ";
			query+=" SET ";
			query+=" author_name = ?, ";	//1
			query+=" author_desc = ? ";		//2
			query+=" WHERE author_id = ? ";	//3
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorName);
		    pstmt.setString(2, authorDesc);
		    pstmt.setInt(3, authorId);
			    
		    iCount = pstmt.executeUpdate();	//query문의 작동 실패 시 iCount = 0
			    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		close();
		    
		return iCount;
		
	}
	
	public int authorDelete(int authorId) {
			int iCount = -1; //어떤 과정에서 실패했는지 확인하기 위해 사용
			
			getConnection();
				
		    try {
		    	String query = "";
				query+=" DELETE ";
				query+=" FROM ";
				query+=" author ";
				query+=" WHERE author_id = ? ";
		    	
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, authorId);
			    
			    iCount = pstmt.executeUpdate();	//query문의 작동 실패 시 iCount = 0
			    		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();
			    
			return iCount;
		
	}
	
	public void getAuthorOne() {
		
	}
	
}
