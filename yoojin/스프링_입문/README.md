## 1주차 - 스프링 입문

### **1. MVC 패턴이란?**

**Model**

- 컨트롤러가 호출을 하면 DB와 연동하여 사용자의 입출력 데이터를 다루는 등 데이터와 연관된 비즈니스 로직을 처리
- 데이터 추출, 저장, 삭제, 업데이트 등의 역할 수행

**View**

- 화면을 그리는 것에 집중
- 사용자와 상호작용 하면서 컨트롤러로부터 받은 Model의 결과 값을 사용자에게 화면으로 출력

**Controller**

- Model과 View를 이어주는 인터페이스 역할
- Model이 데이터를 어떻게 처리할 지 알려주는 역할
- 사용자로부터 View에 요청이 있으면 Controller는 해당 업무를 수행하는 Model을 호출하고, Model이 업무를 모두 수행하면 결과를 View에게 전달

과거엔 View에 모든 비즈니스 로직까지 넣어 코드의 길이가 길어지고 유지 보수 힘들었음

현재 MVC 모델은 코드를 3가지 형태로 나누어 개발을 함

---

### **2. API와 서버? (필수는 아니지만, 아무 API나 사용해보시길 권장 드립니다!)**

API(Application Programming Interface)는 어떤 서버의 특정한 부분에 접속해서 그 안에 있는 데이터와 서비스를 이용할 수 있게 해주는 소프트웨어 도구이다.

**웹 API**

웹은 기본적으로 요청(request)와 응답(response)으로 작동

클라이언트쪽에서 요청을 하면 서버에서 응답을 하는 것

@ResponseBody를 사용해서 ViewResolver를 거치지 않고 HttpMessageConverter를 통해 JSON 스타일로 데이터를 body에 직접 반환하는 방식

---

### **3. RESTful 이란?**

REST(REpresentational State Trasfer)는 웹상에서 사용되는 여러 리소스를 HTTP URI로 표현하고, 해당 리소스에 대한 행위를 HTTP Method로 정의하는 방식이다.

**REST API의 설계 가이드**

- 리소스에 대한 행위는 HTTP Method(POST, GET, PUT, DELETE)로 표현해야 한다
- /(슬래시)는 계층 관계를 나타낼 때 사용한다
- URI 마지막 문자에 /(슬래시)를 사용하지 않는다
- URI에 _(underscore)는 사용하지 않는다. 또한 영어 대문자보다는 소문자를 쓴다
- URI에 동사 대신 명사를 사용한다. 동사는 GET, POST와 같은 HTTP Method를 표현하기 때문
- URI에 파일의 확장자를 포함시키지 않는다

RESTful API는 REST API 설계 가이드를 따라 API를 만드는 것이다.

**RESTful하면 좋은 점?**

RESTul API는 그 자체만으로도 API의 목적이 무엇인지 쉽게 알 수 있다.

따라서 API를 RESTful하게 만들어서 **API의 목적이 무엇인지 명확하게 하기 위해** RESTful함을 지향한다.

---

**동적 환경**

localhost:8080/**hello** 로 접속하면helloController ->  @GetMapping("**hello**") 매칭되면서 hello메소드 실행하게 됨

컨트롤러에서 return 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면(resource/template/리턴된 문자.html)을 찾아서 처리한다.

- 스프링 부트 템플릿 엔진 기본 viewName 매핑
- resource:templates/ +{ViewName}+.html

**정적 컨텐츠**

[localhost:8080/hello-static.html](http://localhost:8080/hello-static.html) 로 들어오면 스프링은

1. hello-static 관련 컨트롤러를 찾는다.
2. 컨트롤러가 없다면, resource/static 에서 hello-static.html을 찾아서 반환



**MVC (Model, View, Controller)**

[localhost:8080/hello-mvc](http://localhost:8080/hello-mvc) 로 들어오면 내장 톰캣 서버가 “hello-mvc가 왔다!” 라고 스프링에게 알림

스프링은 컨트롤러에서 hello-mvc가 매핑되어있는 메소드를 찾아 호출

메소드의 return값(hello-template)과 model(name:spring)을 뷰 리졸버가 받아서 hello-templates.html를 찾아서 템플릿 엔진에게 처리 해달라고 넘김

템플릿 엔진은 html을 변환 해서 웹브라우저에 넘김

****@ResponseBody 사용 원리****

[localhost:8080/hello-api](http://localhost:8080/hello-api) 로 들어오면 내장 톰캣 서버가 스프링에게 hello-api가 왔다고 알림

스프링은 hello-api가 매핑되어있는 메소드를 찾음 → @RespinseBody 발견 → ViewResolver 대신 HttpMessageConverter가 동작하면서 http의 body에 내용을 반환

- return 문자 → StringConverter 동작→ http의 응답에 그대로 넘김
- return 객체 → JsonConverter 동작 → JSON 방식으로 데이터를 만들어서 http응답에 넘김

---

MemberRepository의 4가지 기능

- Member save(Member member);

  →  회원이 저장소에 저장됨

- Optional<Member> findById(Long id);
- Optional<Member> findByName(String name);

  → findById or findByName으로 회원 찾기

- List<Member> findAll();

  → findAll로 지금까지 저장된 회원 리스트 반환
---

**스프링 빈을 등록하는 2가지 방법**

1. **컴포넌트 스캔과 자동 의존관계 설정**
- @Controller, @Service, @Repository 모두 @Component를 포함.
- @Autowired로 의존 관계 주입
- @Component 애노테이션이 (@Component를 포함하는 애노테이션도) 있으면 스프링 빈으로 자동 등록됨

Controller를 통해 외부 요청을 받고,

Service에서 비즈니스 로직을 만들고,

Repository에서 데이터를 저장

memberController→memberService→memberRepository

    @Controller
    public class MemberController {
    
      private final MemberService memberService;
      
      @Autowired
      public MemberController(MemberService memberService){
          this.memberService = memberService;
        }
    …
    }

→ MemberController가 생성 될 때, 스프링 빈에 등록되어있는 MemberService 객체를 가져다가 넣어줌 → 의존 관계 주입

Controller와 Service 연결 → @Autowired

    @Service
    public class MemberService {
      private final MemberRepository  memberRepository;
    
      @Autowired
      public MemberService(MemberRepository memberRepository){
        this.memberRepository = emberRepository;
      }
      …
    }

→ MemberService를 스프링이 생성할 때 @Service를 보고 Service임을 인식하고 스프링 컨테이너에 등록

MemberService 생성자 호출

@Autowired를 보고 MemberService가 생성될 때 스프링 컨테이너에 있는 MemberRepository를 넣어줌 (MemberRepository의 구현체인 MemoryMemberRepository를 Service에 주입해줌)

1. **자바 코드로 직접 스프링 빈 등록하기**

        @Configuration
        public class SpringConfig {
           @Bean
           public MemberService memberService(){
               return new MemberService(**memberRepository());
           }
           @Bean
           public **MemberRepository** memberRepository(){
               return new **MemoryMemberRepository();
           }
       }

스프링이 뜰 때, @Configuration, @Bean을 읽고 MemberService와 MemberRepository를 스프링z 컨테이너에 등록함.

스프링에 등록 되어있는 MemberRepository를 MemberService에 넣어줌 → MemberService(memberRepository())

MemberService를 스프링 빈에 등록하라는 것을 인식

MemberService 생성자에 MemberRepository를 넣어줘야 함

실무에서는 주로 **정형화된** 컨트롤러, 서비스, 리포지토리 같은 코드는 **컴포넌트 스캔**을 사용

**정형화되지 않거나**, 상황에 따라 **구현 클래스를 변경**해야 하면 설정을 통해 **스프링 빈**으로 등록


**스프링 통합 테스트**

@SpringBootTest

→ 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional

→ 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.