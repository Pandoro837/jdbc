package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

		public static void main(String[] args) {
			
			AuthorDao authorDao = new AuthorDao();
			List<AuthorVo> authorList;
			int iCount;
			
			//리스트 출력
			
			authorList = authorDao.getAuthorList();
			//리스트를 for문으로 출력
			printList(authorList);
			
			
			//작가 등록
			AuthorVo authorVo = new AuthorVo("김풍", "웹툰 작가");
			iCount = authorDao.authorInsert(authorVo);
			
			if(iCount > 0) {
				System.out.println(iCount + "건이 등록되었습니다");
				System.out.println("[등록 되었습니다]");
				
				//DB에서 리스트를 다시 가져오기
				authorList = authorDao.getAuthorList();
				
			} else {
				System.out.println("관리자에게 연락하시기 바랍니다");
			}
			
			//출력
			printList(authorList);
			
			//작가 수정
			authorVo = new AuthorVo(7, "주호민", "신과 함께 작가");
			iCount = authorDao.authorUpdate(authorVo);
			
			if(iCount > 0) {
				System.out.println("[" + 7 + "번 수정 완료]");
				
				//DB에서 리스트를 다시 가져오기
				authorList = authorDao.getAuthorList();
				
			} else {
				System.out.println("관리자에게 연락하시기 바랍니다");
			}
			
			
			//리스트 출력
			printList(authorList);
			
			//작가 삭제
			iCount = authorDao.authorDelete(7);
			
			if(iCount > 0) {
				System.out.println("[" + 7 + "번 삭제 완료]");
				
				//DB에서 리스트를 다시 가져오기
				authorList = authorDao.getAuthorList();
				
			} else {
				System.out.println("관리자에게 연락하시기 바랍니다");
			}
			//리스트 출력
			printList(authorList);
			//특정 작가 정보 선택
			authorDao.getAuthorOne();
			
		}

		public static void printList(List<AuthorVo> authorList) {
			for(AuthorVo authorInfo : authorList) {
				System.out.println(authorInfo);
			}
		}

}
