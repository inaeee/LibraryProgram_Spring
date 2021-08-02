package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LibraryApp {
	private static final Scanner scan=new Scanner(System.in);
	
	public static void main(String[] args) {
		ApplicationContext ctx=new AnnotationConfigApplicationContext(JavaConfig.class);
		
		int a=0;
		int b=0;
		boolean loginc=false;
		
		while(true) {
			//맨 처음
			while (a!=1 && a!=2 && a!=10) {
				System.out.println("도서 관리 시스템입니다. 로그인시 이용 가능합니다.");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("10. 종료");
				a=scan.nextInt(); //숫자형 받기
			}
			//로그인
			while(a==1) {
				System.out.println("로그인을 시도합니다.");
				System.out.print("ID를 입력해주세요: ");
				String id=scan.next();
				System.out.print("PASSWORD를 입력해주세요: ");
				String password=scan.next();
				
				UserSystem userSystem=new UserSystem();
				userSystem.setId(id);
				userSystem.setPassword(password);
				loginc=userSystem.login(id, password);
				
				LibraryData libraryData=new LibraryData();
				
				//로그인 성공시
				while(loginc && b!=1 && b!=2 && b!=3 && b!=4 && b!=10) {
					System.out.println("필요한 기능을 선택할 수 있습니다.");
					System.out.println("1. 도서 목록보기");
					System.out.println("2. 도서 이름 검색하기");
					System.out.println("3. 도서 출판사 검색하기");
					System.out.println("4. 도서 추가하기");
					System.out.println("5. 도서 대여하기");
					System.out.println("6. 도서 반납하기");
					System.out.println("7. 사용자 대여 정보 확인하기");
					System.out.println("8. 사용자 비밀번호 변경하기");
					System.out.println("10. 종료");
					b=scan.nextInt(); //숫자형 받기
					
					//도서목록보여주기
					if(b==1) {
						libraryData.libraryList();
						b=0; //다시 초기화
					}
					//도서이름검색하기
					if(b==2) {
						TitleSearch titleSearch=new TitleSearch();
						titleSearch.search(libraryData);
						b=0;
					}
					//도서출판사검색하기
					if(b==3) {
						PublisherSearch publisherSearch=new PublisherSearch();
						publisherSearch.search(libraryData);
						b=0;
					}
					//도서추가하기
					if(b==4) {
						String title="";
						String author="";
						String publisher="";
						System.out.println("추가할 도서의 정보를 입력하세요.");
						System.out.print("제목을 입력하세요: ");
						title=scan.next();
						System.out.print("지은이를 입력하세요: ");
						author=scan.next();
						System.out.print("출판사를 입력하세요: ");
						publisher=scan.next();
						libraryData.newLibrary(title, author, publisher);
						b=0;
					}
					//도서대여하기
					if(b==5) {
						try {
							String libraryFile="C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\librarylist.txt";
							BufferedReader reader=new BufferedReader(new FileReader(libraryFile));
							
							System.out.print("대여할 도서의 제목을 검색하세요: ");
							String searchTitle=scan.next();
							
							String line="";
							while( (line=reader.readLine()) != null) {
								String[] content=line.split(",");
								for(int i=0;i<content.length;i++) {
									if(content[i].equals(searchTitle)) {
										String userFile = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\"+id+".txt";
										BufferedWriter userwriter=new BufferedWriter(new FileWriter(userFile, true));
										userwriter.write(String.format("%s,\n", searchTitle));
								        userwriter.close();
								        System.out.println("\n해당 도서가 대여되었습니다.\n");
									}
								}
							}
						}catch (IOException e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
						b=0;
					}
					//도서 반납하기
					if(b==6) {
						userSystem.userReturn();
						b=0;
					}
					//사용자 대여 정보확인하기
					if(b==7) {
						userSystem.userInfo();
						b=0;
					}
					//사용자 비밀번호 변경하기
					if(b==8) {
						userSystem.changePassword(id, password);
						b=0;
					}
					//종료
					if(b==10) {
						System.out.println("도서관을 이용해주셔서 감사합니다.");
						scan.close();
						return;
					}
				}
			}
			
			//회원가입
			if(a==2) {
				String id="";
				String password="";
				String name="";
				int phonenum=0;
				String address="";
				
				System.out.println("회원가입을 시작합니다.");
				UserSystem newUserSystem=new UserSystem();
				System.out.print("ID를 입력하세요: ");
				id=scan.next();
				System.out.print("PASSWORD를 입력하세요: ");
				password=scan.next();
				System.out.print("이름을 입력하세요: ");
				name=scan.next();
				System.out.print("전화번호를 입력하세요: ");
				phonenum=scan.nextInt();
				System.out.print("주소를 입력하세요: ");
				address=scan.next();
				
				//ID중복검사하기
				if(newUserSystem.sameId(id)){
					System.out.println("\n존재하는 ID입니다.\n");
				}else {
					newUserSystem.userJoin(id, password, name, phonenum, address);
				}
				
				a=0;
			}
			
			//종료
			if(a==10) {
				System.out.println("도서관을 이용해주셔서 감사합니다.");
				scan.close();
				return;
			}
		}
	}
}
