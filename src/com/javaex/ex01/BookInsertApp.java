package com.javaex.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookInsertApp {

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
		    query+= " INSERT INTO book ";
		    query+= " VALUES (seq_book_id.NEXTVAL , ?, ?, ?, ?) ";
		    pstmt = conn.prepareStatement(query);
		    System.out.println("책의 제목을 입력해주세요.");
		    String title = sc.nextLine();
		    System.out.println("책의 출판사를 입력해주세요.");
		    String pubs = sc.nextLine();
		    System.out.println("책의 출판일을 yyyy-mm-dd의 형태로 입력해주세요.");
		    String date = sc.nextLine();
		    Date pub_date = Date.valueOf(date);	//date 형으로 지정해주어야 한다
		    System.out.println("등록된 작가의 번호를 입력해주세요.");
		    int author_id = sc.nextInt();
		    
		    sc.close();
		    
		    pstmt.setString(1, title);
		    pstmt.setString(2, pubs);
		    pstmt.setDate(3, pub_date);
		    pstmt.setInt(4, author_id);
			
		    // 4.결과처리

		    int iCount = pstmt.executeUpdate();
			
		    if(iCount > 0) {
		    	System.out.println(title + "이 등록되었습니다.");
		    } else {
		    	System.out.println("등록에 실패하였습니다.");
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
