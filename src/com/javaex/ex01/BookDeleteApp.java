package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookDeleteApp {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    
			// 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query+= " DELETE FROM book ";
		    query+= " WHERE book_id = ? ";
		    
		    pstmt = conn.prepareStatement(query);
		    System.out.println("삭제할 책의 번호를 입력해주세요.");
		    int book_id = sc.nextInt();
		    sc.close();
		    
		    pstmt.setInt(1, book_id);
		    
		    int iCount = pstmt.executeUpdate();
		    
		    if(iCount > 0) {
		    	System.out.println(book_id + "번 책의 정보가 삭제되었습니다.");
		    } else {
		    	System.out.println("삭제에 실패하였습니다.");
		    }
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

	}

}
