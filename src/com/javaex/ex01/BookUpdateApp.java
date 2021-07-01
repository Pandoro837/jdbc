package com.javaex.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookUpdateApp {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost : ip 위치
			conn = DriverManager.getConnection(url, "webdb", "webdb"); //ip, id, password
		    
			Scanner sc = new Scanner(System.in);
			
			String query = "";
			System.out.println("수정 할 항목을 선택해주세요.");
			System.out.println("1 : 제목 / 2 : 출판사 / 3 : 출판일 / 4 : 작가 번호 / 5 : 종료 ");	//수정할 항목 설정
			String sMenu = sc.nextLine();
			
			switch(sMenu) {
			
			case "1":
				query+=" UPDATE book SET title = ? ";		//제목 변경
				break;
			case "2":
				query+=" UPDATE book SET pubs = ? "; 		//출판사 변경
				break;
			case "3":
				query+=" UPDATE book SET pub_date = ? "; 	//출판일 변경
				break;
			case "4":
				query+=" UPDATE book SET author_id = ? ";	//작가 번호 변경
				break;
			case "5":
				System.out.println("종료합니다.");			//종료
				break;
			default:
				System.out.println("올바른 항목을 선택해주세요.");
				break;
			}
			
			query+=" WHERE book_id = ? ";				//where 절 입력
			pstmt = conn.prepareStatement(query);
			
			System.out.println("수정할 책의 번호를 입력해주세요.");
			int book_id = sc.nextInt();				//작가 번호를 입력 받아 선택
			pstmt.setInt(2, book_id);
			sc.nextLine();
			
			System.out.println("수정할 내용을 입력해주세요."); //선택한 항목을 대체할 내용 입력
			
			if(query.contains("date")) {
				System.out.println("yyyy-mm-dd의 형태로 입력해주세요.");
				String sReplace = sc.nextLine();
				Date replace_date = Date.valueOf(sReplace);	//date 형으로 변경
				pstmt.setDate(1, replace_date);
			} else if (query.contains("author_id")) {
				int iReplace = sc.nextInt();
				pstmt.setInt(1, iReplace);
			} else {
				String sReplace = sc.nextLine();
				pstmt.setString(1, sReplace);
			}
			
			int iCount = pstmt.executeUpdate();
			
			if(iCount > 0) {
				System.out.println( book_id + "번 책이 수정되었습니다.");
			} else {
				System.out.println("수정에 실패했습니다.");
			}
				
			
			sc.close();
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
