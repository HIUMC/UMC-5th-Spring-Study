

spring boot : spring 프로젝트 생성을 쉽게 해줌

spring initializer : 
- Maven & Gradle : 필요한 라이브러리 관리,
빌드, 라이프 사이클까지 관리 (Gradle 많이 사용)

dependencies : 가져올 라이브러리 선택
- ex) Web project이면 Spring Web 선택
- ex) templete engine : timeleap

project를 열때 build.gradle 선택


* project 구성
1. src : main & test 로 나뉨<br>
    * main : java & resources 로 나뉨<br>
        * java에 실제 package랑 소스파일 위치<br>
        *  resources : java 파일을 제외한 xml, properties  설정 파일<br>
    * test : test code 파일 위치


2. build.gradle : plugin, version, source, dependencies 등 설정<br>


3. External Libraries : 웹 어플리케이션을 위한 라이브러리 (기본적으로 필요)<br>
    --> 라이브러리 내에서도 필요한 라이브러리 존재 (의존관계) --> 이를 관리하는것이  Gradle & Maven<br>
    EX) starter-web만 dependencies로 불러와도 밑의 여러개의 라이브러리가 import됨 (tomcat 웹서버 & spring web mvc)<br>

* tomcat 라이브러리 : 내장형 웹서버 --> 라이브러리에서 웹 서버를 가지고 있음

* spring-boot library
1. spring-boot-starter-web
    * spring-boot-starter-tomcat : 톰캣 웹서버
    * spring-webmvc : spring web mvc
2. spring-boot-starter-thymeleaf : 템플릿 엔진(view)
3. spring-boot-starter : spring boot + spring core + logging
   * spring-boot : spring core
   * spring-boot-starter-logging : logback, slf4j

* test library
    * junit, mockito, assertj, spring-test

---

view 환경설정
--
spring은 방대한 기능을 가지고 있기 때문에 spring manual에서 검색할 수 있어야 한다.
정적 페이지 (welcome page) 생성 : src -> main -> resources -> static 에 index.html 생성


=> 정적 페이지이므로 파일을 그대로 서버에 넘겨주는 형태<br>
=> 동적 페이지를 위해선 템플릿 엔진 필요(timeleaf)


* Controller : 리턴값으로 문자를 반환하면 ViewResolver가 화면을 찾아 처리
    * 만약 viewName을 리턴한다면, 'resources:templates/' + {viewName} + '.html' 파일을 찾아 매핑
    * ex) 만약 return "hello"이면, hello.html파일이 열림

    
* @GetMapping("hello") : 웹페이지에서 ~/hello로 반환하면 해당 메서드 호출

---

* 실제 파일로 빌드 방법 :(터미널에서)
    * window : gradle.bat build
    * mac : gradlew build

    * => build 폴더 생성됨 -> libs 폴더 -> ~~.jar 파일 생성
    * java - jar ~~.jar => spring 실행

    * gradle.bat clean : 빌드 폴더 삭제

---

1. 정적 컨텐츠 : 서버에서 하는거 없이 파일을 브라우저에 전달
2. mvc(model, View, controller) & 템플릿 엔진 : 서버에서 프로그래밍하여 html을 동적으로 변환하여 전달
3. API : 데이터 구조 포맷(json, xml 등등)으로 클라이언트에게 데이터를 전달하는 방식
* 2, 3은 동적 컨텐츠



* spring-boot는 main/resources/static 폴더에서 정적 콘텐츠를 찾아 사용
    * static 폴더 내 정적 파일(html)이 그대로 서버로 반환
    * EX) localhost:8080/hello-static.html -> tomcat서버에서 해당 주소를 spring에 넘김
    -> 일단 컨테이너에서 mapping된 컨트롤러가 있는지 찾음 -> 없으면 resources:static 폴더에서 찾아 반환

* MVC : Model, View, Controller
    * model : 비즈니스 로직 (logic과 관련되거나 내부적인걸 처리)
    * view : 화면과 관련된 일(보이는걸 처리)

* parameter를 받을떄, @RequestParam사용, ?로 parameter의 값을 spring으로 전달

* API : 객체를 반환
    * @ResponseBody : http의 response body에 데이터를 직접 반환
    return한 값이 클라이언트에 그대로 올라감 (html로 변환되지 않고 그대로 넘어감 => 소스코드를 봐도 html파일이 아니라 return한 값 그대로)
    * return한 값이 객체일때, json파일로 return하여(key : value) http 응답에 반환

    * ResponseBody일때, viewResolver가 아닌 htmlMessageConverter가 동작
    * 단순 문자면 StringConverter, 객체면 JsonConverter(객체를 json파일로 바꿈)
    * 후에 서버로 응답


---

* 일반적인 웹 어플리케이션 계층 구조
    * 컨트롤러 : 웹 mvc의 컨트롤러
    * 서비스 : 비즈니스 도메인 객체를 가지고 핵심 비즈니스 로직 구현
    * 리포지토리 : db에 접근, 도메인 객체를 db에 저장 & 관리
    * 도메인 : 비즈니스 도메인 객체 저장 & 관리



---

* 테스트 케이스 작성 : 테스트를 할 때, main 메서드를 통해 실행 or 컨트롤러를 통해 실행 => 오래걸리고, 반복 실행 어렵고, 여러 테스트를 실행하기 어려움
* JUnit이라는 프레임워크로 테스트 코드를 만들어 테스트 진행
    * 클래스 레벨에서나, test 폴더 레벨에서 해당 메서드를 실행하여 테스트를 돌릴 수 있는 장점
    