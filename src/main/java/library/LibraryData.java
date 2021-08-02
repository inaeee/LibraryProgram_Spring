package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class LibraryData {
	private String title;
	private String author;
	private String publisher;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	

	//도서 추가
	public void newLibrary(String title, String author, String publisher) {
		try {
			String libraryFile="C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\librarylist.txt";
			BufferedWriter writer=new BufferedWriter(new FileWriter(libraryFile, true));
			writer.write(String.format("%s,%s,%s,\n", title,author,publisher));
			writer.close();
			System.out.println("도서 추가에 성공하였습니다.");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	//도서 목록 보기
	public void libraryList() {
		try {
			String libraryFile="C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\librarylist.txt";
			BufferedReader reader=new BufferedReader(new FileReader(libraryFile));
			
			String line="";
			while( (line=reader.readLine()) != null) {
				String[] content=line.split(",");
				System.out.printf("도서이름:%s\t지은이:%s\t출판사:%s\n", content[0],content[1],content[2]);
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
