package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorInsertApp {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost : ip 위치
			conn = DriverManager.getConnection(url, "webdb", "webdb"); //ip, id, password
		   
			// 3. SQL문 준비 / 바인딩 / 실행	Query문은 sql에서 먼저 테스트 한 후에 가져온다
			String query = "";				//Query문을 문자열로 만들기
			query+=" INSERT INTO author "; 	//query문을 날릴 때는, 해당 query를 줄 단위로 끊어서 +를 통해 붙여준다
			query+=" VALUES(seq_author_id.NEXTVAL, ?, ?) "; //query문의 종결용으로 사용되는 세미콜론;은 빼고 넣는다
			
			pstmt = conn.prepareStatement(query); 			//입력한 query문을 실제 실행할 수 있는 query문으로 바꾼다
			System.out.println("등록할 작가의 이름을 입력해주세요.");
			String author_name = sc.nextLine();
			System.out.println("등록할 작가의 설명을 입력해주세요.");
			String author_desc = sc.nextLine();
			sc.close();	
			
			pstmt.setString(1, author_name); 				  //?값에 입력할 내용을 pstmt.set - 로 지정한다
			pstmt.setString(2, author_desc);			      //int, set 이하 자료형으로 사용하며 
															  //int가 query문에 들어갈 위치를 나타낸다(순서 중요)
			int iCount = pstmt.executeUpdate();      //insert, update, delete에 성공한 갯수를 int로 알려준다
			
			if(iCount > 0) {
				System.out.println(iCount + "건이 저장되었습니다.");
			} else {
				System.out.println("수정에 실패했습니다.");
			}
			
			
		    // 4.결과처리
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
//		        if (rs != null) {
//		            rs.close();
//		        }                
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
