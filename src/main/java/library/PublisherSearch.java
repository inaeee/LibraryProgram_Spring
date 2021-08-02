package library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class PublisherSearch implements Search{
	public void search(LibraryData libraryData) {
		try {
			String libraryFile="C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\librarylist.txt";
			BufferedReader reader=new BufferedReader(new FileReader(libraryFile));
			
			String line="";
			//출판사로 검색하기
			Scanner scan=new Scanner(System.in);
			System.out.print("도서 출판사를 검색하세요: ");
			String searchPublisher=scan.next();
			
			//가지고 있는 도서 중 해당 도서가 있는 지 찾기
			int a=0;
			while ( (line=reader.readLine()) != null) {
				String[] content=line.split(",");
				for(int i=0;i<content.length;i++) {
					if(content[i].equals(searchPublisher)) {
						System.out.printf("도서이름:%s\t지은이:%s\t출판사:%s\n", content[i-2],content[i-1],content[i]);
						a++;
					}
				}
			}
			//해당 도서가 없을 경우
			if(a==0) {
				System.out.println("해당 도서를 찾을 수 없습니다.");
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
