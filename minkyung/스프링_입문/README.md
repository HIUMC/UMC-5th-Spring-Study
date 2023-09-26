1. 프로젝트 환경설정
   1. [프로젝트 생성](#프로젝트-생성)
   2. [라이브러리 살펴보기](#라이브러리-살펴보기)
   3. [View 환경설정](#view-환경설정)
   4. [빌드하고 실행하기](#빌드하고-실행하기)
2. 스프링 웹 개발 기초
   1. [정적 컨텐츠](#정적-컨텐츠)
   2. [MVC와 템플릿 엔진](#mvc와-템플릿-엔진)
   3. [API](#api)
3. 회원 관리 예제 - 백엔드 개발
   1. [비즈니스 요구사항 정리](#비즈니스-요구사항-정리)
   2. [회원 도메인과 리포지토리 만들기](#회원-도메인과-리포지토리-만들기)
   3. [회원 리프지토리 테스트 케이스 작성](#회원-리포지토리-테스트-케이스-작성)
   4. [회원 서비스 개발](#회원-서비스-개발)
   5. [회원 서비스 테스트](#회원-서비스-테스트)
4. 스프링 빈과 의존관계
   1. [컴포넌트 스캔과 자동 의존관계 설정](#컴포넌트-스캔과-자동-의존관계-설정)
   2. [자바 코드로 직접 스프링 빈 등록하기](#자바-코드로-직접-스프링-빈-등록하기)
5. 회원 관리 예제 - 웹 MVC 개발
   1. [회원 웹 기능 - 홈 화면 추가](#회원-웹-기능---홈-화면-추가)
   2. [회원 웹 기능 - 등록](#회원-웹-기능---조회)
   3. [회원 웹 기능 - 조회](#회원-웹-기능---조회)
6. 스프링 DB 접근 기술
   1. [H2 데이터베이스 설치](#h2-데이터베이스-설치)
   2. [순수 Jdbc](#순수-jdbc)
   3. [스프링 통합 테스트](#스프링-통합-테스트)
   4. [스프링 JdbcTemplate](#스프링-jdbctemplate)
   5. [JPA](#jpa)
   6. [스프링 데이터 JPA](#스프링-데이터-jpa)
7. AOP
   1. [AOP가 필요한 상황](#aop-가-필요한-상황)
   2. [AOP 적용](#aop-적용)


# 프로젝트 생성

- 사전 준비물
    - Java 11 설치
    - IDE : IntelliJ 또는 Eclipse 설치

### 스프링 프로젝트 생성하기 
✅ [스프링 부트 스타터 사이트](https://start.spring.io)로 이동하기

    ⚠️ 주의 ⚠️ 스프링 부트 3.0 사용 시

    1. Java 17 이상 사용
    2. javax 패키지 이름 → jakarta 로 변경
    3. H2 데이터베이스 2.1.214 버전 이상 사용

- 프로젝트 선택
  - Project : Gradle - Groovy Project
  - Spring Boot : 2.3.x
  - Language : Java
  - Packaging : Jar
  - Java : 11
- Project Metadata
  - groupId : hello
  - artifactId : hello-spring
- Dependencies : Spring Web, Thymeleaf

  ![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbrWWU2%2FbtssTr9JgWb%2F7fjKnpDSNcEugAVOj9wcY0%2Fimg.png)
 

✅ IntelliJ 에서 프로젝트 생성하기
1. 스프링 부트 스타터 사이트에서 GENERATE → 폴더 생성
2. IntelliJ로 폴더 열기
3. Settings - Build, Execution, Deployment - Gradle 설정 변경
    1. Build and run using : Gradle → IntelliJ IDEA
    2. Run tests using : Gradle → IntelliJ IDEA

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcI1j3z%2FbtssLDxrt2f%2FVKqS6mitf2pcgGk7aWoTbK%2Fimg.png)

4. Run

- 실행되는 코드
```java
package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		// 실행하려는 클래스 이름 써주기
	}
}
```

### 톰캣 서버 실행 확인하기
✅ 웹 브라우저에서 http://localhost:8080 으로 톰캣 서버 실행 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkOPo6%2FbtssJl4Bn9Y%2FTp43qwyaRryrCbSkj5nAGK%2Fimg.png)

→ 이렇게 나오면 성공

- 실행 멈췄을 때 톰캣 서버

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F5pNzD%2FbtssLJj7MuS%2Fkwb4W7rsLsHiCGUX9Fk080%2Fimg.png)

→ 연결 안됨

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 라이브러리 살펴보기

- build.gradle - dependencies
- External Libraries

→ 이전과 다르게 라이브러리에 웹 서버 (WAS) 내장되어 있음

### 스프링 부트 라이브러리 

- spring-boot-starter-web
    - spring-boot-starter-tomcat : 톰캣 (웹서버)
    - spring-webmvc : 스프링 웹 MVC
- spring-boot-starter-thymeleaf : 타임리프 템플릿 엔진 (View)
- spring-boot-starter (공통) : 스프링 부트 + 스프링 코어 + 로깅
    - spring-boot
        - spring-core
    - spring-boot-starter-logging
        - logback, slf4j


### 테스트 라이브러리

- spring-boot-starter-test
    - junit : 테스트 프레임워크
    - mockito :  목 라이브러리
    - assertj : 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
    - spring-test : 스프링 통합 테스트 지원

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# View 환경설정

### Welcome Page 만들기

✅ src - resources - static - index.html 생성
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
Hello
<a href="/hello">hello</a>
</body>
</html>
```

✅ 웹 브라우저 접속 (http://localhost:8080)

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbkH23a%2Fbtss3F9nvam%2F1PkOIi9AczPVlv92k1XL70%2Fimg.png)

→ 성공 (정적 페이지)

- https://spring.io/ 접속해서 작동 방식 살펴보기
    - Projects - Spring Boot - Learn - Spring Boot Features - 7. Developing Web Applications - 7.1.6. Welcome Page

### Thymeleaf 템플릿 엔진

- [thymeleaf 공식 사이트](https://www.thymeleaf.org/)
- [스프링 공식 튜토리얼](https://spring.io/guides/gs/serving-web-content/)
- [스프링부트 메뉴얼](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines)

1. Controller 생성
    1. src/main/java/hello.hellospring 에 controller package 생성
    2. controller package 밑에 HelloController 클래스 생성

```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // key: data , value: hello!!
        // hello.html 에서 ${data} 호출 시 hello!! 반환
        model.addAttribute("data", "hello!!");

        // return 값은 src/resources/templates 내 html 의 이름과 같아야 함 : rendering
        return "hello";
    }
}
```

2. src/resources/templates 에 hello.html 생성

```html
<!DOCTYPE html>
<!-- thymeleaf 문법 사용 가능 -->
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<!-- key 값이 data 인 value 반환 : hello!! (HelloController 에서 정의) -->
<p th:text="'안녕하세요. ' + ${data}">안녕하세요. 손님</p>
</body>
</html>
```

3. 웹 브라우저 확인
- GetMapping

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FKfnJL%2Fbtss91X6CZN%2FA4DLJdTKvTq8bqU6nxTSPK%2Fimg.png)

- 웹에서 HTML 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fx6owd%2Fbtss8lvZKh9%2F5GcRokQM3hCKmcIpsBxLY1%2Fimg.png)

→ hello!! 로 바뀌어 있다

- 동작 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fce0FAZ%2Fbtss70Fy6jF%2FaGuifE6uvxRRKIkH8N3vb1%2Fimg.png)

→ Controller 에서 return 값으로 문자 반환 시 viewResolver 가 화면을 찾아 처리

- 스프링 부트 템플릿 엔진 기본 viewName 매핑
- resources:templates/ + {ViewName} + .html

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 빌드하고 실행하기

[Windows 명령 프롬프트(cmd)에서 ls, clear 명령어 사용하는 방법](https://somjang.tistory.com/entry/Windows-%EB%AA%85%EB%A0%B9-%ED%94%84%EB%A1%AC%ED%94%84%ED%8A%B8cmd%EC%97%90%EC%84%9C-ls-clear-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)

    ⚠️ IntelliJ 빌드 종료 후 실행 ⚠️

- 명령 프롬프트 (CMD)
1. 빌드하기

```bash
# cmd 에서 ls 명령어 사용
doskey ls = dir

# 이동
cd C:\Users\alsrud\Downloads\hello-spring\hello-spring

# 목록 조회
ls

# 빌드
gradlew.bat build
```

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb3iOi1%2FbtstayWGO7x%2FeOgO2Zknbe1Xnt1aax6kd0%2Fimg.png)

➡️ Windows : gradlew.bat

➡️ Mac : gradlew

2. 빌드 후 폴더 열기

```bash
cd build/libs
```

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHE9kU%2Fbtstbb1d9l4%2FjVOEdkzppnXcAdGpkB1azk%2Fimg.png)

3. jar 파일 실행

```bash
java -jar hello-spring-0.0.1-SNAPSHOT.jar
```

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FIltpq%2Fbtstlheql86%2FmXcLpFaTUhIeDM04iw7jvK%2Fimg.png)

4. 웹 브라우저에서 실행 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOlQf3%2FbtstlMZDZSD%2F7sz4LL2PIx1tMkuCtH1C30%2Fimg.png)

- 잘 안될 때 ?
```bash
gradlew.bat clean build
```
➡️ build 폴더 지웠다가 다시 생성

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 정적 컨텐츠

- [스프링 부트 정적 컨텐츠 기능](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content)

1. src/resources/static 에 hello-static.html 생성

```html
<!DOCTYPE html>
<html>
<head>
    <title>static content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
정적 컨텐츠 입니다.
</body>
</html>
```

2. 웹 브라우저에서 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbld4oU%2FbtstkpEPtQQ%2FPWUgk3hQx9eNfKxhha41k0%2Fimg.png)

- 동작 방식

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbk4jAr%2FbtstmuZJfTZ%2FILszpKhnSKkqkewu76vKz1%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# MVC와 템플릿 엔진

- MVC : Model, View, Controller

1. src/main/java/hello.hellospring/controller/HelloController 에 GetMapping 추가

```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    ...

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
```

2. src/resources/templates 에 hello-template.html 추가

```html
<!DOCTYPE html>
<!-- thymeleaf 문법 사용 가능 -->
<html xmlns:th="http://www.thymeleaf.org">
<body>
<!-- key 값이 name 인 value 반환 (HelloController 에서 정의) -->
<p th:text="'hello ' + ${name}">hello! empty</p>
</body>
</html>
```

3. 웹 브라우저에서 확인  
![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FumFZY%2Fbtstg5mj7my%2FQLw38G00ICehVSCTkx3agK%2Fimg.png)

- 동작 방식

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdpgmQv%2FbtstnxhB890%2FLDdSAAzskQnXOw9CEpHSHK%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# API

### GetMapping("hello-string")

1. src/main/java/hello.hellospring/controller/HelloController 에 GetMapping 추가

```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    ...

    @GetMapping("hello-string")
    // ResponseBody : http body 부분에 직접 데이터 입력
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
}
```

2. 웹 브라우저 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXGOzI%2FbtstlOc3Apq%2FIkYkQoQlzpxKPuKFk7voWk%2Fimg.png)

➡️ View 를 사용하지 않음

### GetMapping("hello-api")

3. src/main/java/hello.hellospring/controller/HelloController 에 GetMapping 추가

```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    ...

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체 생성
        hello.setName(name);
        return hello;
        // 기본으로 JSON 반환
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
```

4. 웹 브라우저 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcmwJRS%2Fbtstg1qHjq1%2Ftu25CgLWuv2LDKP46dkPmK%2Fimg.png)

- 동작 방식

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fba345u%2FbtstnwXiFGq%2FKIJvAFO1IQFxyNF169RH91%2Fimg.png)

- @ResponseBody 사용
    - HTTP 의 Body 에 문자 내용을 직접 반환
    - viewResolver 대신 HttpMessageConverter 동작
    - 기본 문자 처리 : StringHttpMessageConverter
    - 기본 객체 처리 : MappingJackson2HttpMessageConverter

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 비즈니스 요구사항 정리

- 데이터 : 회원ID, 이름
- 기능 : 회원 등록, 조회
- 아직 데이터 저장소가 선정되지 않음 (가상의 시나리오)

### 일반적인 웹 애플리케이션 계층 구조

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxZS2c%2FbtstkvdZenO%2FeBO6c69Dclf8K8vwtAJ1TK%2Fimg.png)

- 컨트롤러 : 웹 MVC 의 컨트롤러 역할
- 서비스 : 핵심 비즈니스 로직 구현
- 리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB 에 저장하고 관리
- 도메인 : 비즈니스 도메인 객체
    - 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

### 클래스 의존관계

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FXivay%2Fbtstyg7jjLA%2FwbnXeGPtZaaOlPJI2kgeg0%2Fimg.png)

➡️ 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계  

➡️ 데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민중인 상황으로 가정

➡️ 개발을 진행하기 위해서 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소 사용

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 도메인과 리포지토리 만들기

### 회원 객체

1. src/main/java/hello.hellospring 에 domain package 추가
2. domain package 에 Member class 추가

```java
package hello.hellospring.domain;

public class Member {

    private Long id;
    private String name;

    // Alt + Insert -> Getter and Setter 자동으로 함수 생성
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### 회원 리포지토리 인터페이스

1. src/main/java/hello.hellospring 에 repository package 생성
2. repository package 에 MemberRepository Interface 생성

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // id 로 회원 찾기
    Optional<Member> findByNames(String name); // name 으로 회원 찾기
    List<Member> findAll(); // 지금까지 저장한 모든 회원 리스트 반환
}
```

### 회원 리포지토리 메모리 구현체

1. repository package 에 MemoryMemberRepository class 생성
```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
// 동시성 문제가 고려되지 않음 : 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // store 에 저장 전 id setting
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optinal.ofNullable 사용 시 Null 값이 반환되어도 사용 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByNames(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
```

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 리포지토리 테스트 케이스 작성

    💡 개발한 기능 테스트 시 자바의 Main 메서드를 통해 실행하거나, 웹 애플리케이션의 컨트롤러를 통해 해당 기능 실행한다. 
    이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복하기 어려우며, 여러 테스트를 한 번에 실행하기 어렵다는 단점이 있다. 
    자바는 JUnit 이라는 프레임워크로 테스트를 실행하여 이러한 문제를 해결한다.

### 회원 리포지토리 메모리 구현체 테스트

1. test/java/hello.hellospring 에 repository package 생성
2. repository package 에 MemoryMemberRepositoryTest class 생성

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 순서는 보장이 안됨 -> 각 테스트가 끝날 때마다 repository 비워주기
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // member 이랑 result 랑 같은지 비교
    }

    @Test
    public void findbyName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByNames("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
```

➡️ @AfterEach : 한 번에 여러 테스트를 실행하면 메모리 DB 에 직전 테스트 결과가 남을 수 있어 다음 테스트가 실패할 가능성이 있다. @AfterEach 를 사용하면 각 테스트가 종료될 때마다 이 기능을 실행한다. 여기서는 메모리 DB 에 저장된 데이터를 삭제한다.

➡️ 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

- 테스트 결과
![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbL036i%2Fbtsts1wTZI8%2FzsotDpsLUHE1Q4zHF9A1P0%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 서비스 개발

1. src/main/java/hello.hellospring 에 service package 생성
2. service package 에 MemberService 생성
```java
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X
        // Null 일 가능성이 있으면 Optional 사용
        memberRepository.findByNames(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 서비스 테스트

    💡 Ctrl + Shift + T : 테스트 쉽게 만들기 (윈도우)

### MemberService 변경

```java
...
public class MemberService {

    private final MemberRepository memberRepository;
    // 외부에서 넣어주기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
		...
}
```

### MemberServiceTest

```java
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try {
              memberService.join(member2);
              fail("예외가 발생해야 합니다.");
          } catch (IllegalStateException e) {
              Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
          }
*/    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
```

- @BeforeEach
  - 각 테스트 실행 전 호출
  - 테스트 간 영향이 없도록 항상 새로운 객체 생성
  - 새로운 의존관계 형성

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 컴포넌트 스캔과 자동 의존관계 설정

    💡 회원 컨트롤러가 회원 서비스와 회원 리포지토리를 사용할 수 있게 의존관계를 준비

- 스프링 빈을 등록하는 2가지 방법
    - 컴포넌트 스캔과 자동 의존관계 설정
    - 자바 코드로 직접 스프링 빈 등록하기

- 컴포넌트 스캔 원리
    - @Component 애노테이션이 있으면 스프링 빈으로 자동 등록
    - @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문
    - @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록
        - @Controller
        - @Service
        - @Repository

### MemberController

- src/main/java/hello.hellospring/controller 에 MemberController 생성

```java
package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Controller 등록
@Controller
public class MemberController {

    private final MemberService memberService;

    // 자동 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
```

➡️ 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.

➡️ 이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired 에 의해 스프링이 주입해준다.

> 참고 : helloController 는 스프링이 제공하는 컨트롤러이기 때문에 스프링 빈이 자동 등록된다. @Controller 가 있으면 자동 등록된다
>

### MemberService

```java
...

@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    // 외부에서 넣어주기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
		...
}
```

> 참고 : 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아 주입한다. 생성자가 1개만 있으면 @Autowired 는 생략할 수 있다
>

### MemoryMemberRepository

```java
...

@Repository
public class MemoryMemberRepository implements MemberRepository{
// 동시성 문제가 고려되지 않음 : 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    ...
}
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/41d467f2-5e7c-44b9-bd8f-32790fc9fc0b/Untitled.png)

### MemoryMemberRepository
```java
...

@Repository
public class MemoryMemberRepository implements MemberRepository{
// 동시성 문제가 고려되지 않음 : 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    ...
}
```

- 작동 방식
![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcbDWi3%2FbtstvROyJpF%2Fe6OGA0Dzj9F0S4OQ1zswTk%2Fimg.png)

➡️ memberService 와 memberRepository 가 스프링 컨테이너에 스프링 빈으로 등록되었다.

> 참고 : 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다. (유일하게 하나만 등록해서 공유) 따라서 같은 스프링 빈이면 모두 같은 인스턴스이다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다
>

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 자바 코드로 직접 스프링 빈 등록하기

    💡 회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거하고 진행

- src/main/java/hello.hellospring 에 SpringConfig class 생성

```java
package hello.hellospring;

import hello.hellospring.service.MemberService;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

➡️ 이 강의에서는 향후 메모리 리포지토리를 다른 리포지토리로 변경할 예정이므로, 컴포넌트 스캔 방식 대신 자바 코드로 스프링 빈을 설정 : 기존 코드 변경 없이 사용 가능
> 참고 : XML 로 설정하는 방식도 있지만, 최근에는 잘 사용하지 않으므로 생략한다
>

> 참고 : DI 에는 필드 주입, setter 주입, 생성자 주입 3가지 방법이 있다. 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다
>

> 참고 : 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다. 이때, 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 한다면 설정을 통해 스프링 빈으로 등록한다
>

> 주의 : @Autowired 를 통한 DI 는 helloController, memberService 등과 같이 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다
>

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 웹 기능 - 홈 화면 추가

### 홈 컨트롤러 추가

- src/main/java/hello.hellospring/controller 에 HomeController class 생성

```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```

### 회원 관리용 홈

- src/main/resources/templates 에 home.html 생성

```html
<!DOCTYPE html>
<!-- thymeleaf 문법 사용 가능 -->
<html xmlns:th="http://www.thymeleaf.org">
<body>

<div class="container">
    <div>
        <h1>Hello Spring</h1>
        <p>회원 기능</p>
        <p>
            <a href="/members/new">회원 가입</a>
            <a href="/members">회원 목록</a>
        </p>
    </div>
</div>

</body>
</html>
```

> 참고 : 컨트롤러가 정적 파일보다 우선순위가 높다
>

- 웹 브라우저 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fcm6PF6%2FbtstvRgL4aA%2FK1k48UIc2BUX9Xl8YLkWI1%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 웹 기능 - 등록

### 회원 등록 폼 개발

#### ✅ 회원 등록 폼 컨트롤러

- src/main/java/hello.hellospring/controller 에 MemberController class 생성

```java
package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Controller 등록
@Controller
public class MemberController {

    private final MemberService memberService;

    // 자동 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
}
```

#### ✅ 회원 등록 폼 HTML

1. src/main/resources/templates 에 members directory 생성
2. members directory 에 createMemberForm.html 생성

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
</head>
<body>

<div class="container">
    <form action="/members/new" method="post">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="이름을
입력하세요">
        </div>
        <button type="submit">등록</button>
    </form>
</div>

</body>
</html>
```

### 회원 등록 컨트롤러

#### ✅ 웹 등록 화면에서 데이터를 전달 받을 폼 객체

- src/main/java/hello.hellospring/controller 에 MemberForm class 생성

```java
package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

#### ✅ 회원 컨트롤러에서 회원을 실제 등록하는 기능

- MemberController 에 코드 추가
```java
...

// Controller 등록
@Controller
public class MemberController {

    ...

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
}
```

- 웹 브라우저 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqYVDZ%2FbtstwFUCYsN%2FXMje0ykQrxTdnKeQdtHDo1%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 회원 웹 기능 - 조회

### 회원 컨트롤러에서 조회 기능 추가

- MemberController 에 code 추가

```java
...

@Controller
public class MemberController {

    ...

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
```

### 회원 리스트 HTML 생성

- src/resources/templates/members 에 memberList.html 생성
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>회원 조회</title>
</head>
<body>

<div class="container">
  <div>
    <table>
      <thead>
      <tr>
        <th>#</th>
        <th>이름</th>
      </tr>
      </thead>
      <tbody>
      <tr th:each="member : ${members}">
        <td th:text="${member.id}"></td>
        <td th:text="${member.name}"></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
```

- 웹 브라우저 확인

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F07rMK%2Fbtstq5Ntcqa%2FtzGM9QUQpSsEELCzxuw7C1%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# H2 데이터베이스 설치

- 개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공

### 1. H2 데이터베이스 설치
#### ✅ 웹 브라우저에서 다운로드

- [Archive Downloads (h2database.com)](https://www.h2database.com/html/download-archive.html)
    - 1.4.200

#### ✅ 다운받은 bin 폴더에서 h2.bat 실행

```bash
cd C:\Users\alsrud\Downloads\H2
cd bin
h2.bat
```

#### ✅ 시작 설정

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeCCUBH%2FbtstyiLZJUh%2FMAIssgwkFKKIkVOLfg5d5k%2Fimg.png)

- 데이터베이스 파일 생성 방법
  - jdbc:h2:~/test (최초 한 번)
  - 홈에 생성된 ~/test.mv.db 파일 확인 → 지워주기 (rm)
  - 이후부터는 jdbc:h2:tcp://localhost/~/test 로 접속

- 접속 화면

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkZZCB%2FbtstxkctJyP%2Fx8dkKxsRmcA8XTUDmWyuKK%2Fimg.png)

### 2. 테이블 생성
#### ✅ SQL 문 작성 (테이블 생성)

```sql
drop table if exists member CASCADE;
create table member
(
id bigint generated by default as identity,
name varchar(255),
primary key (id)
);
```

- 실행 화면

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblGWLe%2FbtstzlaDD1S%2FQfeC0MZKtgEetaLCp5Dyc0%2Fimg.png)

→ MEMBER table 생성 완료

#### ✅ SQL 문 작성 (값 대입)

```sql
insert into member(name) values('spring');
insert into member(name) values('spring2');
```

- 실행 화면

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FctDcuJ%2FbtstMkIsbe2%2FZLTWTGzllGcDYAsukqXehK%2Fimg.png)

### 3. Intellij 에 ddl.sql 파일 추가
![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbue96G%2FbtstLNRElIQ%2FSPvvOI6ScoPa3tRf3YpwiK%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 순수 Jdbc

- 고대의 방식이라고 한다 😑

### 환경 설정

#### ✅ build.gradle 파일에 jdbc, h2 데이터베이스 관련 라이브러리 추가

```json
implementation 'org.springframework.boot:spring-boot-starter-jdbc'
runtimeOnly 'com.h2database:h2'
```

- 추가 화면

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FQxTjW%2FbtstMAy3e16%2FKPZNgUsNbPxVWPhBbRpnfk%2Fimg.png)

#### ✅ 스프링 부트 데이터베이스 연결 설정 추가 (resources/application.properties)

```json
spring.datasource.url=jdbc:h2:tcp://localhost/~/test
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
```

- 추가 화면

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkzfXb%2FbtstG5ftqZL%2FkjQXMxK4aqfNmhmJcD95B0%2Fimg.png)

> 주의 : 스프링부터 2.4 부터는 spring.datasource.username=sa 를 꼭 추가해주어야 한다. 그렇지 않으면 Wrong user name or password 오류가 발생한다. 참고로 다음과 같이 마지막에 공백이 들어가면 같은 오류가 발생한다. spring.datasource.username=sa 공백 주의, 공백은 모두 제거해야 한다.
>

> 참고 : IntelliJ 커뮤니티(무료) 버전의 경우 [application.properties](http://application.properties) 파일의 왼쪽이 다음 그림과 같이 회색으로 나온다. 엔터프라이즈(유료) 버전에서 제공하는 스프링의 소스 코드를 연결해주는 편의 기능이 빠진 것인데, 실제 동작하는 데는 아무런 문제가 없다.
>

## Jdbc 리포지토리 구현

> 주의 : 스프링 부트 2.4 부터는 spring.datasource.username=sa 를 꼭 추가해야 한다. 추가하지 않을 시 Wrong user name or password 오류가 발생한다. 마지막에 공백이 들어가도 같은 오류가 발생한다.>
> 

### Jdbc 회원 리포지토리

> 주의 : 이렇게 JDBC API 로 직접 코딩하는 것은 20년 전 이야기로, 참고만 하고 넘어가기
>

#### ✅ Jdbc 회원 리포지토리 생성

- src/main/java/hello.hellospring/repository 에 JdbcMemberRepository class 생성

```java
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findByNames(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
```

    💡 DataSourceUtils import error → gradle refresh 하기

#### ✅ 스프링 설정 변경

- src/main/java/hello.hellospring/service/SpringConfig

```java
package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.service.MemberService;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
```

➡️ MemoryMemberRepository 대신 JdbcMemberRepository

- 스프링 설정 이미지

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FAtXYc%2FbtstRs1pVTB%2FrKa8Mjdh2WCu4GRGLXbGB1%2Fimg.png)

→ 개방 - 폐쇄 원칙 ( OCP, Open - Closed Principle ) : 확장에는 열려 있고, 수정/변경에는 닫혀 있다

→ 스프링의 DI ( Dependencies Injection ) 을 사용하면 기존 코드를 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다

→ 데이터를 DB 에 저장하므로 스프링 서버를 다시 실행해도 데이터가 안전하게 저장된다

- 실행 화면

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FtgJMx%2FbtstRIXiY9z%2F1aixEmlqOiAYeHkWJQGUP0%2Fimg.png)

→ 데이터베이스에 저장했던 데이터가 잘 들어와 있다

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 스프링 통합 테스트

- 스프링 컨테이너와 DB 까지 연결한 통합 테스트 진행하기

### 회원 서비스 스프링 통합 테스트

#### ✅ test/java/hello.hellospring/service 에 MemberServiceIntegrationTest 생성

```java
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
```

#### ✅ 테스트 하기 (@Transactional 주석 처리한 상태)

1. MEMBER 테이블 데이터 삭제하기

```sql
delete from member
```

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbwu2bM%2FbtstRLnbkZ0%2FWnSCxTBzAsjHDOTGhlTEVK%2Fimg.png)

2. IntelliJ 에서 테스트 실행하기

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRdf7x%2FbtstNyWdvNn%2FkLc75jokJ3feoHbxsWbbN1%2Fimg.png)

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHujWT%2FbtstTblpzYb%2FYEtsrcxpzsVnKBIc4TtTAk%2Fimg.png)

- @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행
- @Transactional : 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후 항상 롤백 (DB에 데이터가 남지 않아 다음 테스트에 영향을 주지 않음)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 스프링 JdbcTemplate

- 순수 Jdbc 와 동일한 환경설정
- 스프링 JdbcTemplate & MyBatis 라이브러리 ➡️ JDBC API 에서 본 반복 코드 대부분 제거, 그러나 SQL 은 직접 작성해야 함

### 스프링 JdbcTemplate 회원 리포지토리 작성
#### ✅ src/main/java/hello.hellospring/repository 에 JdbcTemplateMemberRepository 생성

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired // 생성자가 하나만 있으면 Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // SimpleJdbcInsert : sql insert 문을 쓰지 않고도 insert 가능
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByNames(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
```

### SpringConfig 설정 변경

#### ✅ jdbcTemplate 을 사용할 수 있도록 스프링 설정 변경

→ 근데 원래 설정이랑 같음 : return new JdbcTemplateMemberRepositoy(dataSource); 추가해주는 것

```java
...

@Configuration
public class SpringConfig {

    ...

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
```

#### ✅ 테스트 하기

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbqx9tA%2FbtsuelnbIpJ%2FpNy6uR7kh5xkk8NBY1tqfK%2Fimg.png)

→ 성공

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# JPA

    💡현재 해결되지 않은 것💡 : SQL 은 개발자가 직접 작성 ➡️ JPA 사용 시 해결 가능

    ✅ JPA 는 기존의 반복 코드 & 기본적인 SQL 처리, 실행
    ✅ JPA 사용 시 SQL 과 데이터 중심의 설계에서 객체 중심의 설계로 전환 가능
    ✅ JPA 사용 시 개발 생산성 향상

### JPA, H2 데이터베이스 관련 라이브러리 추가

- build.gradle 파일 수정하기 ➡️ 수정 후 refresh 필수 !

```java
...

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	//implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	runtimeOnly 'com.h2database:h2'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

...
```

→ spring-boot-starter-data-jpa 가 jdbc 관련 라이브러리를 포함하고 있기 때문에 jdbc 는 제거 가능

### 스프링 부트에 JPA 설정 추가

- resources/application.properties 파일 수정

```java
...
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
```

> 주의 : 스프링부트 2.4 부터는 spring.datasource.username=sa 를 꼭 추가해주어야 한다. 그렇지 않으면 오류가 발생한다.
>

- show-sql : JPA 가 생성하는 SQL 출력
- ddl-auto : JPA 가 제공하는 자동 테이블 생성 기능
  - none : 기능 제거
  - create : 엔티티 정보를 바탕으로 테이블 생성

### JPA 엔티티 매핑

- src\main\java\hello\hellospring\domain\Member 설정 변경

```java
package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity // JPA 가 관리하는 엔티티라는 뜻
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 가 알아서 ID 생성
    private Long id;
    
    @Column(name = "username") // DB 에 있는 column 명이 username 이 됨
    private String name;

    ...
}
```

### JPA 회원 리포지토리 생성

- src/main/java/hello.hellospring/repository 에 JpaMemberRepository 생성

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByNames(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
```

### 서비스 계층에 트랜잭션 추가

- src/main/java/hello.hellospring/service 의 MemberService 파일 수정

```java
...

@Transactional
public class MemberService {

    ...
}
```

→ JPA 를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 함

### 스프링 설정 변경

- SpringConfig 파일 수정

```java
...

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
```

- 테스트 하기

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdmlnW%2FbtsugTwTpNE%2FAAHFTkUNKi676AkRzA2gRK%2Fimg.png)

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# 스프링 데이터 JPA

    💡 스프링 데이터 JPA 💡
    ➡️ 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발 완료 가능
    ➡️ 기본 CRUD 기능 제공

    ⚠️주의⚠️
    스프링 데이터 JPA 는 JPA 를 편리하기 사용하도록 도와주는 기술이므로 JPA 를 먼저 학습해야 함

- 앞의 JPA 설정을 그대로 사용

### 스프링 데이터 JPA 회원 리포지토리

- src/main/java/hello.hellospring/repository 에 SpringDataJpaMemberRepository 인터페이스 생성

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스는 다중 상속 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByNames(String name);
}
```

### 스프링 설정 변경

- SpringConfig 코드 수정

```java
package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.service.MemberService;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 만들어 놓은 SpringDataJpaRepository 구현체가 세팅된다 -> 스프링 빈에 등록
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}
```

- 테스트 하기

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb6Dbpe%2Fbtsuh4MH3uu%2FXi1oP2I9YWkCp3e3ktAl4K%2Fimg.png)

→ 성공

### 스프링 데이터 JPA 제공 클래스

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FDAckP%2FbtsuAt53Skc%2F5hl5kY2TztrwubqiXESVV1%2Fimg.png)

→ 클래스 구현 없이도 개발 가능

### 스프링 데이터 JPA 제공 기능

1. 인터페이스를 통한 기본 CRUD
2. findByName(), findByEmail() 처럼 메서드 이름 만으로 조회 가능
3. 자동 페이징 기능

> 참고 : 실무에서는 JPA 와 스프링 데이터 JPA 를 기본으로 사용하고, 복잡한 동적 쿼리는 Querydsl 이라는 라이브러리를 사용한다. Querydsl 사용 시 쿼리를 자바 코드로 안전하게 작성할 수 있고, 동적 쿼리도 편리하게 작성할 수 있다. 이외는 JPA 가 제공하는 네이티브 쿼리를 사용하거나, 스프링 JdbcTemplate 을 사용하면 된다.
> 

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# AOP 가 필요한 상황

- 모든 메소드의 호출 시간을 측정하고 싶다면?

### MemberService 회원 조회 시간 측정 코드 추가

- src/main/java/hello.hellospring/service/MemberService 코드 추가
```java
...

@Transactional
public class MemberService {

    ...

    // 회원 가입
    public Long join(Member member) {

        // 시작할 때 시간 측정
        long start = System.currentTimeMillis();

        try {
            // 같은 이름이 있는 중복 회원 X
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            // 끝날 때 시간 측정
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    ...

    // 전체 회원 조회
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    ...
}
```

- 테스트 하기

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbsgjRP%2FbtsudROtKs3%2FDkOtHnSkA2VGIAGZyR9eZ1%2Fimg.png)

### 문제점

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmCNpP%2FbtsuCGLbYvx%2FPptx7If8mcHYv5XBerEdWk%2Fimg.png)

→ 시간 측정 기능은 공통 관심 사항

→ 시간 측정 로직과 핵심 비즈니스 로직이 섞여 유지보수가 어려움

→ 시간 측정 로직을 별도의 공통 로직으로 만들기는 힘듦

→ 시간 측정 로직 변경 시 모든 로직을 변경해야 함

➡️ AOP 가 필요 !!

<!----------------------------------------------------------->
<!----------------------------------------------------------->

# AOP 적용

- AOP : Aspect Oriented Programming (관점 지향 프로그래밍)
- 공통 관심 사항 (cross-cutting concern) vs. 핵심 관심 사항 (core concern) 분리

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkUsny%2FbtsueN6dR5s%2FfqBseKLsmeINv6hgBKgnuK%2Fimg.png)

### 시간 측정 AOP 등록

1. MemberService 에 추가했던 시간 측정 코드 삭제
2. src/main/java/hello.hellospring 에 aop 패키지 생성
3. 생성한 패키지 밑에 TimeTraceAop 클래스 생성

```java
package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect; // AOP 사용
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
```

- 테스트 하기 (회원 조회 했을 때)

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcPyNZq%2FbtsuGvWV51W%2FHkAy58oiVrPYARk7l2zS81%2Fimg.png)

→ AOP 로 시간 측정 완료

### 스프링 AOP 동작 방식

- AOP 적용 전 의존 관계

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FWL77d%2FbtsuG90lkEx%2FCe1k6OTDMomlyJq26Kx1TK%2Fimg.png)

- AOP 적용 후 의존 관계

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbDywJt%2FbtsueNkRFGs%2FoRyKNz9UPoHUxxvKrY38xK%2Fimg.png)

→ 가짜 memberService 먼저 호출 (프록시 memberService)

- AOP 적용 전 전체 그림

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbRDs0Q%2FbtsuglON7ba%2Fis6FCwTpw2eM306L4c3A0k%2Fimg.png)

- AOP 적용 후 전체 그림

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FW6Fkd%2FbtsuflO8cAV%2FebGWV6aCMx0MC4oDGAZwXk%2Fimg.png)

### 해결한 문제

→ 핵심 관심 사항(회원 기능) & 공통 관심 사항(시간 측정 로직) 분리

→ 시간 측정 로직을 별도의 공통 로직으로 생성 (코드 변경 용이)

→ 원하는 적용 대상 선택 가능

<!----------------------------------------------------------->
<!----------------------------------------------------------->
