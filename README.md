# LibraryProgram_Spring

#### 📢 Spring Framework 를 사용하여 도서관 회원 대출 프로그램을 개발하였다.
🖥️ 개발환경 : Java, Spring Boot    
📜 Spring ? 
> Java 플랫폼을 위한 오픈소스 애플리케이션 프레임워크로 엔터프라이즈급 애플리케이션을 개발하기 위한 모든 기능을 종합적으로 제공한다.
> IOC (Inversion of Control) 제어의 역전: 기존 사용자가 모든 작업을 제어하는 것을 특별한 객체에 모든 것을 위임하여
> 객체의 생성부터 생명주기 등 모든 객체에 대한 제어권이 넘어간 것이다.

* DL (Dependency Lookup) 의존성 검색 : 컨테이너에서는 객체들을 관리하기 위해 별도의 저장소에 빈을 저장하는데
컨테이너에서 제공하는 API를 이용하여 사용하고자 하는 빈을 검색하는 방법.     
* DI (Dependency Injection) 의존성 주입 : 객체가 서로 의존하는 관계가 되게 의존성을 주입하는 것.    
객체지향프로그램에서 의존성이란 하나의 객체가 어떠한 다른 객체를 사용하고 있음을 의미한다.    
각 클래스 사이에 필요로 하는 의존관계를 빈 설정 정보를 바탕으로 컨테이너가 자동으로 연결해주는 것.    

<br>

1. JavaConfig.java : Bean 
2. LibraryApp.java : 앱의 전체적인 기능. 로그인(도서 목록, 검색, 추가, 대여, 반납, 변경 등), 회원가입, 종료
3. LibraryData.java : 내부 txt 파일을 데이터로 취급하고 도서 추가, 목록 확인을 제공
4. PublisherSearch.java : 내부 txt 파일을 데이터로 취급하고 출판사를 기준으로 검색
5. TitleSearch.java : 내부 txt 파일을 데이터로 취급하고 도서 제목을 기준으로 검색
6. UserSystem.java : 회원 관리에 대한 기능.       
userJoin : 사용자별 id를 기준으로 정보txt 파일을 생성하여 회원가입        
sameId : id가 중복되면 고유성을 잃기 때문에 중복을 검사하여 방지한다.       
login : id와 password가 일치하면 로그인 성공        
useInfo : 사용자가 대여 중인 도서의 정보       
userReturn : 대여중인 도서를 확인 후 특정 도서를 반납한다.       
changePassword : 사용자 비밀번호 변경         
