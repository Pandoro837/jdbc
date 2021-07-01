package com.javaex.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSelectApp {

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
					query+=" SELECT ";
					query+=" bk.book_id, ";
					query+=" bk.title, ";
					query+=" bk.pubs, ";
					query+=" bk.pub_date, ";
					query+=" aut.author_id, ";
					query+=" aut.author_name, ";
					query+=" aut.author_desc ";
					query+=" FROM ";
					query+=" book bk, ";
					query+=" author aut ";
					query+=" WHERE bk.author_id = aut.author_id ";
							
					
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					
				    // 4.결과처리
					
					while(rs.next()) {
						int book_id = rs.getInt("book_id");		//rs.get - 의 파라미터는 컬럼명으로 입력한다
						String title = rs.getString("title");
						String pubs = rs.getString("pubs");
						Date pub_date = rs.getDate("pub_date");	//date 자료형은 date로 받아야 한다
						int author_id = rs.getInt("author_id");
						String author_name = rs.getString("author_name");
						String author_desc = rs.getString("author_desc");
						System.out.println(book_id + ", " + title + ", " + pubs + ", " + pub_date + ", " + author_id 
								+ ", " + author_name + ", " + author_desc);
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
