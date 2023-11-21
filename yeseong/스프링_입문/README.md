## 스프링 입문
start.spring.io docs 적극 활용할 것

---

### 프로젝트 환경 설정
#### 프로젝트 생성
start.spring.io > 기본 틀 생성 후 빌드
#### 라이브러리
gradle/maven 빌드 툴 -> 의존 관계를 관리  
gradle - 의존 관계가 있는 라이브러리를 함께 다운로드

### View 환경설정
#### Welcome Page
src > main > resources > static > index.html 생성 후 html 코드 작성
#### 빌드하기
- 콘솔에서 파일 위치로 접근
- ll 로 파일 확인 후
- ./gradlew build
- cd build/libs 
- ls -arlth 로 .jar 파일 확인
- java -jar hello-spring-0.0.1-SNAPSHOT.jar
  

- ./gradlew clean build <- 빌드 폴더 삭제
### 스프링 웹 개발 기초
#### 정적 컨텐츠
경로 : main > resources > static  
파일 추적 후 그대로 반환
#### MVC와 템플릿 엔진
MVC : Model View Controller  
Controller -> 내부 로직 관계  
View -> 화면 표현
#### API
@ResponseBody -> body에 객체 직접 반환 (<body> 아님)  
@ResponseBody 사용 시 ViewResolver 사용 x 
### 회원 관리 예제 - 백엔드 개발
- 컨트롤러 - 웹 MVC의 컨트롤러
- 서비스 - 핵심 로직 구현
- 리포지토리 - 데이터베이스에 접근/ 도메인 객체를 DB에 저장/관리
- 도메인 - 비즈니스 도메인 객체
### 스프링 빈
#### 등록하는 법
- 컴포넌트 스캔으로 자동 의존관계 설정
  - @Component 애노테이션이 있으면 스프링 빈으로 자동 등록.
  - @Component 를 포함하는 애노테이션도 스프링 빈으로 자동 등록 (@Controller @Service @Repository)
  - @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
- 자바 코드로 직접 스프링 빈 등록하기
### DB접근 방법
Jdbc-> JPA -> Spring Data JPA  
JPA: (Java Persistence API)  
ORM (Object Relational Mapping)
- POJO 클래스를 Relational 데이터베이스에 mapping하는 기술
즉 순수 자바 클래스 <> Relational DB 사이의 징검다리 같은 역할
(그래서 Object Relational Middleware로도 불린다)

JDBC만을 사용한다면

1) DB 접근을 위한 쿼리 작성
2) DB에서 데이터 가져오기
3) 해당 데이터를 POJO 클래스에 넣기

위 작업들을 모두 프로그래머가 수행해야한다.  
이를 편하게 해주는 것이 JPA
이러한 내부 작업들은 JPA가 수행하고, 프로그래머는 그 결과로 생성된 POJO 클래스를 곧바로 이용하게 됨

### 스프링 데이터 JPA

save(Entity), deletebyId 등 기본적인 CRUD 혹은FindById( id ) 와 같은 자주 쓰이는 내용들을
JpaRepository 인터페이스를 상속받는 것으로 모두 사용할 수 있게 함

### AOP
- 모든 메소드의 호출 시간을 측정할 때
- 공통 관심 사항 vs 핵심 관심 사항 분리
- 공통 관심 사항 : 인프라 로직 
- 핵심 관심 사항 : 비즈니스 로직 ,핵심관심사항을 깔끔하게 유지