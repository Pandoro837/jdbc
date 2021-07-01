package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorUpdateApp {

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
					
					boolean flag = true; 			//while문 작동용
					while(flag) {
						String query = "";
						System.out.println("수정 할 항목을 선택해주세요");
						System.out.println("1 : 작가이름 / 2 : 작가소개 / 3 : 수정종료");	//수정할 항목 설정
						String sMenu = sc.nextLine();
						
						switch(sMenu) {
						
						case "1":
							query+=" UPDATE author SET author_name = ? ";	//작가 이름 변경
							break;
						case "2":
							query+=" UPDATE author SET author_desc = ? "; 	//작가 설명 변경
							break;
						case "3":
							System.out.println("종료합니다");
							flag = false;
							return;
						default:
							System.out.println("올바른 항목을 선택해주세요");
							continue;
						}
						query+=" WHERE author_id = ? ";				//where 절 입력
						pstmt = conn.prepareStatement(query);
						
						System.out.println("수정할 작가의 번호를 입력해주세요");
						int author_id = sc.nextInt();				//작가 번호를 입력 받아 선택
						pstmt.setInt(2, author_id);
						sc.nextLine();
						
						System.out.println("수정할 내용을 입력해주세요");
						String sReplace = sc.nextLine();			//선택한 이름, 설명을 대체할 내용 입력
						pstmt.setString(1, sReplace);
						
						int iCount = pstmt.executeUpdate();
						
						if(iCount > 0) {
							System.out.println("작가 번호 " + author_id + "번이 수정되었습니다.");
						} else {
							System.out.println("수정에 실패했습니다.");
						}
						
					}
					
					
					/* 
//					case 1
					while(flag) {
						String query = "";	// query 값이 null이면 이후에 query+= 값이 들어가지 않는다
						System.out.println("수정 할 항목을 선택해주세요");
						System.out.println("1 : 작가이름 / 2 : 작가소개 / 3 : 수정종료");
						String sMenu = sc.nextLine();
						String sWhere = "WHERE author_name = ";
						
						switch(sMenu) {
							
							case "1":
								query+=" UPDATE author SET author_name ";	
								break;
							case "2":
								query+=" UPDATE author SET author_desc "; 
								break;
							case "3":
								System.out.println("종료합니다");
								flag = false;
								return;
							default:
								System.out.println("올바른 항목을 선택해주세요");
								continue;
						}
						
						System.out.println("수정할 작가의 이름을 입력해주세요");
						sWhere+= "'" + sc.nextLine() + "'";
						
						
						System.out.println("수정 할 내용을 입력해주세요");
						String sReplace = "'" + sc.nextLine() + "'";
						query+=" = " + sReplace + " ";		
						query+=sWhere;
						
						System.out.println(query);
						
						pstmt = conn.prepareStatement(query);
						
						int iCount = pstmt.executeUpdate();
						
						if(iCount > 0) {
							System.out.println(sWhere + "이(가) 수정되었습니다.");
						} else {
							System.out.println("수정에 실패했습니다.");
						}
					}*/
					
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
