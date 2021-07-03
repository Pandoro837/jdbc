package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelectApp {

	public static void main(String[] args) {
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
			query+= " SELECT author_id, ";
			query+= "		 author_name,";
			query+= "		 author_desc";
			query+= " FROM author ";
			
			pstmt = conn.prepareStatement(query);			
			rs = pstmt.executeQuery();    //select
		    
			// 4.결과처리
			while(rs.next()) {
				int authorId = rs.getInt(1);						//컬럼명이 아니라 컬럼 순서를 int 값으로 적어도 작동한다
				String authorName = rs.getString(2);				//컬럼 순서를 지정할 경우, 컬럼의 이름이 바뀌어 있어도 작동하는 장점이 있다
				String authorDesc = rs.getString(3);
				
/*				int authorId = rs.getInt("author_id");				//컬럼 이름으로 지정할 경우, 컬럼의 순서가 변경되어도 작동한다
				String authorName = rs.getString("author_name");	
				String authorDesc = rs.getString("author_desc");*/
				
				System.out.println(authorId + ", " + authorName + ", " + authorDesc);
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

	}

}
