package com.javaex.ex05;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BookDao bookDao = new BookDao();
		List<BookVo> bookList;
		int iCount;
		
		//리스트 생성
		bookList = bookDao.getBookList();
		//리스트를 출력
		printList(bookList);
		
//		//책 등록
//		BookVo newBookVo = new BookVo("살인자의 기억법", "문학동네", "2013-07-25", 6);
//		iCount = bookDao.bookInsert(newBookVo);
//		//리스트를 출력
//		bookList = bookDao.getBookList();
//		printList(bookList);
//		
//		//책 수정
//		BookVo updateBookVo = new BookVo(22, "title", "pubs", "2013-07-25", 7);
//		iCount = bookDao.getUpdate(updateBookVo);
//		//리스트를 출력
//		bookList = bookDao.getBookList();
//		printList(bookList);
//				
//		//책 삭제
//		iCount = bookDao.delete(9);
//		//리스트를 출력
//		bookList = bookDao.getBookList();
//		printList(bookList);
		
		//책 검색
		System.out.println("검색어를 입력해주세요.");
		System.out.print("검색어 > ");
		String searchWord = "%" + sc.nextLine() + "%";
		
		bookList = bookDao.search(searchWord);
		printList(bookList);
		
		sc.close();
	}
	
	public static void printList(List<BookVo> bookList) {
		for(BookVo bookInfo : bookList) {
			System.out.println(bookInfo);
		}
	}
	
}
