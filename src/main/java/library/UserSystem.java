package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class UserSystem {
	private String id;
	private String password;
	private String name;
	private int phonenum;
	private String address;
	Scanner scan=new Scanner(System.in);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public int getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(int phonenum) {
		this.phonenum=phonenum;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	
	//회원가입
	public void userJoin (String id, String password, String name, int phonenum, String address) {
		try {
			//버퍼입출력
			BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\userdata.txt", true));
			//사용자별 정보 입력할 파일만들기
			String userFile = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\"+id+".txt";
			BufferedWriter userwriter=new BufferedWriter(new FileWriter(userFile, true));
			//회원가입 정보입력
			writer.write(String.format("%s,%s,%s,%d,%s,\n", id,password,name,phonenum,address));
			
			//남아있는 데이터를 모두 출력
			writer.flush();
			writer.close();
			
			System.out.println("회원 가입에 성공하였습니다.");
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	//id가 중복되면 데이터 판별하기 힘드니까 id 중복여부 확인하기
	//name은 동명이인이 있을 수 있으니 기준이 될 수 없다.
	public boolean sameId(String id) {
		try {
			//버퍼입출력
			BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\userdata.txt"));
			
			//id확인
			String line="";
			while( (line=reader.readLine()) != null) {
				String[] user=line.split(","); //구분
				String sameIds=user[0];
				
				if(sameIds.equals(id)) {
					reader.close();
					return true;
				}

			}
			
			reader.close();
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return true;
		}
	}
	
	//로그인
	//id와 password가 일치하면 로그인 성공
	public boolean login(String id, String password) {
		try {
			//파일 읽기
			BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\userdata.txt"));
			
			String line="";
			while ((line=reader.readLine()) != null) {
				String[] user=line.split(",");
				String passId=user[0];
				String passPw=user[1];
				
				//id확인
				if(passId.equals(id)) {
					//password확인, id 다음 인덱스
					if(passPw.equals(password)) {
						System.out.println("로그인에 성공하였습니다.");
						reader.close();
						return true;
					}
					//password불일치
					else {
						System.out.println("PASSWORD 오류로 로그인에 실패하였습니다.");
						reader.close();
						return false;
					}
				}
				//id불일치
				else {
					System.out.println("ID 오류로 로그인에 실패하였습니다.");
					reader.close();
					return false;
				}
			}
			reader.close();
			return false;
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return true;
		}
	}
	
	//사용자 대여정보 확인하기
	public void userInfo() {
		try {
			String userFile = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\"+id+".txt";
			BufferedReader reader=new BufferedReader(new FileReader(userFile));
			
			String line="";
			while( (line=reader.readLine()) != null) {
				String[] content=line.split(",");
				System.out.printf("대여 중인 도서: %s\n", content[0]);
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	//사용자 도서 반납하기
	public void userReturn() {
		String userFile = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\"+id+".txt";
		String userFileNo = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\"+id+"No.txt";
			
		BufferedReader breader=null;
		BufferedWriter bwriter=null;
			
		userInfo(); //대여중인 도서 보이기
		System.out.print("반납할 도서를 입력하세요: ");
		String libraryReturn=scan.next();
			
		try {
			breader=new BufferedReader(new FileReader(userFile));
			bwriter=new BufferedWriter(new FileWriter(userFileNo));
				
			String line="";
			while((line=breader.readLine()) != null) {
				if(line.contains(libraryReturn)) {
					line.replace(libraryReturn,"");
				}
				bwriter.write(line+"\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
			
		finally {
			try {
				if(breader!=null) {
					breader.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}try {
				if(bwriter!=null) {
					bwriter.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
			
		//파일 정리
		File userFile2=new File(userFile);
		userFile2.delete();
		File userFileNo2=new File(userFileNo);
		userFileNo2.renameTo(userFile2);
			
		System.out.println("도서가 반납되었습니다.");
	}

	//사용자 비밀번호 변경
	public void changePassword(String id, String password) {
		String userFile = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\userdata.txt";
		String userFileNo = "C:\\Users\\inaee\\Desktop\\2021-1\\스프링프레임워크\\userdataNo.txt";
		
		BufferedReader breader=null;
		BufferedWriter bwriter=null;
		
		System.out.print("새로운 비밀번호를 입력하세요: ");
		String newPassword=scan.next();
		
		try {
			breader=new BufferedReader(new FileReader(userFile));
			bwriter=new BufferedWriter(new FileWriter(userFileNo));
			
			String line="";
			while( (line=breader.readLine()) != null) {
				if(line.contains(password)) {
					line=line.replace(password, newPassword);
				}
				bwriter.write(line+"\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
		
		finally {
			try {
				if(breader!=null) {
					breader.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}try {
				if(bwriter!=null) {
					bwriter.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		//파일 정리
		File userFile2=new File(userFile);
		userFile2.delete();
		File userFileNo2=new File(userFileNo);
		userFileNo2.renameTo(userFile2);
		
		System.out.println("비밀번호가 변경되었습니다.");
	}
	
}