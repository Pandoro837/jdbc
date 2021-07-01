package com.javaex.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookSelectOneApp {

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
				query+=" SELECT ";
				query+=" book_id, ";
				query+=" title, ";
				query+=" pubs, ";
				query+=" pub_date, ";
				query+=" author_id ";
				query+=" FROM book ";
				query+=" WHERE book_id = ? ";
				
				
				pstmt = conn.prepareStatement(query);
				System.out.println("조회할 책의 번호를 입력하세요.");
				int bookId = sc.nextInt();
				sc.close();
				pstmt.setInt(1, bookId);
				rs = pstmt.executeQuery();
				
			    // 4.결과처리
				
				while(rs.next()) {
					int book_id = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					Date pub_date = rs.getDate("pub_date");	//date 자료형은 date로 받아야 한다
					int author_id = rs.getInt("author_id");
					
					System.out.println(book_id + ", " + title + ", " + pubs + ", " + pub_date + ", " + author_id );
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
