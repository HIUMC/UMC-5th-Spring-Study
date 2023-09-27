# 스프링 입문

## 섹션1

Project Metadat

- Group: 기업 도메인 명

main이랑 test 폹더를 구분하는게 디폴트

개발 트렌드에서 test code는 중요하다.

java파일을 제외하고는 다 resources에 넣어라.

build.gradle: 버전 설정 및 라이브러리 끌고 옴

```jsx
repositories {
	mavenCentral()
}
-> mavenCentral이라는 공개된 사이트에서 다운받으라는 의미를 담는다. 
```

Java는 메인 메소드에서 실행하는 기반으로 돌아감.

Tomcat started on port(s): 8080 (http) with context path '’ → http 8080을 띄운거다.

톰캣이라는 웹 서버가 내장되어 있는데, 이 톰캣을 띄우면서 스프링부트 서버가 실행된다.

spring-boot-starter-web을 다운받으면, 의존관계에 있는 모든 필요한 라이브러리들을 다 땡겨온다.

우측 Gradle의 Dependencies를 보면 관계에 있는 라이브러리들을 정리해서 볼 수 있음. 이 때, 중복해서 가져오지 않고 이미 가져온 것들은 다시 가져오지 않음.

과거에는 서버와 개발 코드가 분리되어 있었지만, 현재는 소스 코드 라이브러리에서 웹 서버 관련된 내용을 내장하고 있음.

라이브러리 하나를 빌드해서 웹 서버에 올리면 끝남. (별도의 설정 필요 X)

현업에서는 log를 따로 써야, 로그 파일을 남겨야 한다. (System.out.println 쓰지 마라.)

logback을 많이 씀. 성능이 좋다. sl4j랑 logback 같이 씀.

test는 junit을 많이 씀. 현재는 junit5임 mockito + assertj는 테스트를 쉽게 하기 위한 용이하게 하는 라이브러리

**스프링 부트 라이브러리**

- spring-boot-starter-web
    - spring-boot-starter-tomcat: 톰캣(웹서버)
    - spring-webmvc: 스프링 웹 MVC
- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
    - spring-boot
        - spring-core
    - spring-boot-starter-logging
        - logback, slf4j

**테스트 라이브러리**

- spring-boot-starter-test
    - junit: 테스트 프레임워크
    - mockito: 목 라이브러리
    - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
    - spring-test: 스프링 통합 테스트 지원

https://spring.io/ 에 의하면, index.html을 먼저 찾는다.

빌드 후 실행하기

./gradlew build → 폴더 위치 이동 후 → java -jar ~~~.jar

## 섹션2

정적 컨텐츠 → 파일을 웹 브라우저에 내려줌

MVC → (Model, View, Controller) → 서버에서 변형해서 내려주는 방식

View는 화면에 관련된 일만 / 비즈니스 로직과 관련된 내용은 컨트롤러 / 화면에 넘겨줄 때 쓰는게 모델

API → json이라는 데이터 구조 포맷으로 정보를 전달

스프링부트는 기본이 json으로 반환


ResponseBody가 붙어있으면, 응답에 그대로 데이터를 넘기려고 함. 객체로 반환하려고 하면 기본이 json 방식으로 http 응답에 반환하는게 기본 정책임

Getter / Setter이라는 메소드를 통해 사용하는 걸 자바 빈 표준 방식임. (프로퍼티 접근 방식)

## 섹션3

Optional에서 바로 빼오는게 get()임

테스트 순서는 의존성을 가지지 않게 해야 한다. → 공용 데이터들이나 저장소를 클리어 해줘야 할 필요가 있다.

@AfterEach → 테스트가 끝날 때마다 어떤 지정된 동작을 하도록 하게 해야함.

TDD → 테스트주도개발

테스트 코드 없이 개발하지 마라.

cmd + shift + t: 테스트 코드 자동생성 커맨드

테스트 코드는 과감하게 코드를 써도 된다.

@BeforeEach → 테스트 전 진행할 내용

```jsx
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

이렇게 외부에서 주입하도록 바꾸는 걸 -> DI(의존성 주입) 이라고 한다.
```

## 섹션4

스프링 컨테이너에 딱 하나만 등록하고, 그걸 사용할 수 있도록 하면 된다.

@Autowired는 스프링 빈에 등록되어 있는 객체를 필요하다고 판단하고 연결함. → 의존성을 주입하는 것 (Dependency Injection)

@Service 어노테이션을 쓰면 스프링이 이걸 서비스네 하고 스프링 컨테이너에 등록한다.

컨트롤러를 통해 외부 요청 받고 / 서비스에서 비즈니스 로직 수행 / 레포지토리에서 데이터 연결

**스프링 빈을 등록하는 방법**

- 컴포넌트 스캔과 자동 의존관계 설정
    - @Component와 관련된 어노테이션이 있으면, 스프링 컨테이너에 빈으로 자동 등록한다.
    - @Autowired를 통해 등록된 스프링 빈을 연결할 수 있다.
    - 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다. (즉, 유일하게 하나만 등록해서 공유한다.) → 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 할 수 있지만, 기본적으로 대부분 싱글톤을 사용한다.
    - SpringBootApplication이라는 파일의 패키지 경로에 포함된 하위 경로에 있는 컴포넌트들만 스캔함.
- 자바 코드로 직접 스프링 빈 등록하기
    - @Bean 어노테이션으로 직접 스프링 빈을 등록할 수 있다.

의존성 주입

- 필드 주입, Setter 주입, 생성자 주입 총 3가지 방법이 있다.
- 필드 주입 예시 : @Autowired private MemberService memberservice; // 권장하지 않음
- setter주입은 public으로 호출이 되므로, 중간에 바뀌어버린다면 문제가 생길 수 있다.

- 실무에서는 정형화된 컨트롤러, 서비스, 레포지토리 같은 코드는 컴포넌트 스캔 사용 / 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 한다면 설정을 통해 스프링 빈으로 등록한다.
- 스프링 빈에 등록되어 있지 않으면, @Autowired 동작하지 않음.

## 섹션6

### JDBC

자바는 기본적으로 DB랑 붙으려면 JDBC 드라이버가 있어야 함.

JDBC는 데이터 커넥션 실행하고 계속 끊어주는 걸 선언해줘야 함. 아니면 Connection Full 에러 뜸


SOLID 원칙이 있음

그 중, OCP가 중요하다. (확장에는 열려있고, 수정/변경에는 닫혀있다.) 객체지향의 다형성을 의미

### **통합테스트**

- 테스트용 DB를 따로 구축하거나, 로컬DB를 사용

트랜잭션이라는 개념이 있기에, 커밋이 있어야 DB에 실제로 저장됨.

테스트에서 @Transaction걸면 테스트가 끝나고 롤백됨. → 다음 테스트에 영향을 주지 않음.

@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.

<aside>
💡 가급적이면 순수한 단위 테스트가 효율적인 테스트이다.

</aside>

### JDBCTemplate

MyBatis와 비슷한 라이브러리로 JDBC API에서 반복 코드를 제거한 것임. 단, SQL은 직접 작성해야 한다.

### JPA

SQL과 데이터 중심의 설계에서 객체 중심의 설계로 전환되어 개발 생산성 좋음.

JPA는 EntityManager로 모든 것이 동작한다.

em.persist(); → 영구저장하다. 영속하다. 의미

em.createQuery();

위와 같은 방식 JPQL이다.

Spring Data JPA를 사용한 Hibernate라는 오픈소스 구현체가 사용된다.

### Spring Date JPA

<aside>
💡 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술이다. JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 한다.

</aside>

## 섹션7

AOP가 필요한 상황

- 모든 메소드의 호출 시간을 측정하고 싶다면?
- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?

공통으로 사용되는 로직이지 핵심 로직이 아닌 것들을 공통 관심 사항이라 부른다.

시간을 측정하는 로직과 비즈니스 로직이 섞여 있으면 유지보수가 어렵다.

AOP(Aspect Oriented Programming)

공통 관심 사항과 핵심 관심 사항을 분리한다.

@Aspect를 통해 AOP로 하겠다.

이 때, 스프링 컨테이너에 빈으로 등록해야 함. → @Component 어노테이션 활용 필요

@Around → 원하는 곳에만 사용하겠다는 것임.

Ex) @Around(”execution(* hello.hellospring..*(.))”) : 패키지 하위에다 다 적용하라는 의미

**AOP 장점**

- 핵심 관심 사항과 공통 관심 사항을 분리한다.
- 원하는 적용 대상을 선택할 수 있다.

→ 보통 패키지 레벨로 많이 한다.

**AOP 동작 방식 (프록시를 활용한)**

DI가 있기에 가능한 것이다.