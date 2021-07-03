package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드
	
	
	//생성자
	
	//메소드 겟터셋터
	
	//메소드 일반
	
	//DB 정보를 arrayList에 넣어 전달
	public List<AuthorVo> getAuthorList() {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo> ();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			
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
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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
		
		return authorList;
	}
	
	//DB에 정보를 입력
	public int authorInsert(String authorName, String authorDesc) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int iCount = -1; //어떤 과정에서 실패했는지 확인하기 위해 사용
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			//query문
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
		    		
		    // 4.결과처리

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
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
		
		return iCount;
	}
	
	public int authorUpdate(int authorId, String authorName, String authorDesc) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int iCount = -1; //어떤 과정에서 실패했는지 확인하기 위해 사용
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			//query문
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
			    		
				
	    // 4.결과처리
		   
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
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
		
		return iCount;
		
	}
	
	public int authorDelete(int authorId) {
		// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int iCount = -1; //어떤 과정에서 실패했는지 확인하기 위해 사용
			
			try {
			    // 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
			    // 2. Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
			    // 3. SQL문 준비 / 바인딩 / 실행
				//query문
				String query = "";
				query+=" DELETE ";
				query+=" FROM ";
				query+=" author ";
				query+=" WHERE author_id = ? ";
				
			    pstmt = conn.prepareStatement(query);
			    pstmt.setInt(1, authorId);
			    
			    iCount = pstmt.executeUpdate();	//query문의 작동 실패 시 iCount = 0
			    		
			    // 4.결과처리

			} catch (ClassNotFoundException e) {
			    System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} finally {
			   
			    // 5. 자원정리
			    try {
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
				
			return iCount;
		
	}
	
	public void getAuthorOne() {
		
	}
	
}
