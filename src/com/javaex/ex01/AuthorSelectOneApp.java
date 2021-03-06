package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorSelectOneApp {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
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
			query+= " WHERE author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			System.out.println("보고싶은 작가의 번호를 입력하세요");
			int author_id = sc.nextInt();
			sc.close();
			pstmt.setInt(1, author_id);
			
			rs = pstmt.executeQuery();
			
		    // 4.결과처리

			while(rs.next()) {
				author_id =rs.getInt("author_id");
				String author_name =rs.getString("author_name");
				String author_desc =rs.getString("author_desc");
			
				System.out.println(author_id + ", " + author_name + ", " + author_desc);
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
