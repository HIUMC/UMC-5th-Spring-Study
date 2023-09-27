# SPRING BOOT

<aside>
💡 스프링 입문편

</aside>

- **👣 Section 1 - 프로젝트 환경 설정**
    - **프로젝트 생성**
        
        요즘은 [https://start.spring.io](https://start.spring.io) 에서 기본 툴을 받고 파일을 open해서 시작한다.
        
        - build.gradle이란?
            
            버전 설정 & 라이브러리 땡겨 오는 역할 정도만 이해하자
            
        
        ```jsx
        @SpringBootApplication
        public class HelloSpringApplication {
            public static void main(String[] args) {
               SpringApplication.run(HelloSpringApplication.class, args);
            }
        ```
        
    - **라이브러리 살펴보기**
        
        그냥 하나만 불러도 연쇄적으로 다 이어서 불려 나온다.
        
    - **view 환경 설정**
        
        > **검색 방법**
        > 
        
        (예) Welcome Page어떻게 만들지?
        
        - spring.io접속 -> project > spring boot로 들어가기 -> learn 들어가서 내 버전의 reference document들어가기 -> 원하는 내용 찾기
        
        ```jsx
        @GetMapping("hello")
        public String hello(Model model) {
            model.addAttribute("data", "hello!!");
            return "hello";
        }
        ```
        
    - **빌드하고 실행하기**
        
        콘솔로 이동(git bash/cmd) ->  해당 파일 안에 들어가기 -> gradlew 치고 엔터 -> gradlew build하고 엔터 -> cd build ->  cd libs -> 무슨무슨 SNAPSHOT.jar 파일이 있음 -> java -jar (파일 이름).jar 하면 실행 완
        
        나중에 배포 시에는 무슨무슨 SNAPSHOT.jar 파일만 서버에 넣고 java -jar로 실행하면 됨
        
        + 오류시에는 gradlew clean 하면 build가 삭제됨. 그 후에 다시해보기
        
- **👣 Section 2 - 스프링 웹 개발 기초**
    - **정적 컨텐츠**
        
        > resources>static에 hello-static.html을 만들고
        > 
        > 
        > [localhost:8080/hello-static.html](http://localhost:8080/hello-static.html이라고)을 입력하면 서버의 반응은?
        > 
        
        ![Untitled](SPRING%20BOOT%2068ea037869b94df18216e34ee4e174cc/Untitled.png)
        
        1. controller에 hello-static이 mapping되어 있는지 확인 (우선순위를 갖는 다는 이야기)
        2. 없으면 resources 안에 있는 이름이 일치하는 html을 찾아서 띄워준다.
    - **MVC와 템플릿 엔진**
        
        MVC : Model View Controller
        
        ![Untitled](SPRING%20BOOT%2068ea037869b94df18216e34ee4e174cc/Untitled%201.png)
        
        ```jsx
        @GetMapping("hello-mvc")
        public String helloMvc(@RequestParam("name") String name, Model model) {
            model.addAttribute("name", name);
            return "hello-template";
        }
        ```
        
    - **API**
        - MVC : View를 찾아 템플릿 엔진을 통해 화면을 렌더링 해 직접적인 html을 띄움
        - API : 오직 필요한 데이터를 바로 넘겨줌
        
        > getter setter 단축키 : Alt + Insert
        > 
        
        ```jsx
        @GetMapping("hello-api")
        @ResponseBody
            public Hello helloApi(@RequestParam("name") String name) {
                Hello hello = new Hello();
                hello.setName(name);
                return hello;
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
        ```
        
        ---
        
        ![Untitled](SPRING%20BOOT%2068ea037869b94df18216e34ee4e174cc/Untitled%202.png)
        
        이때 넘겨주는 값이 객체면 JsonConverter가, 단순 문자열이면 StringConverter가 작동
        
- **👣 Section 3 - 회원 관리 예제 (백엔드 개발)**
    - **비즈니스 요구사항 정리**
        - 데이터: 회원ID, 이름
        - 기능: 회원 등록, 조회
        
        ![Untitled](SPRING%20BOOT%2068ea037869b94df18216e34ee4e174cc/Untitled%203.png)
        
         - 컨트롤러: 웹 MVC의 컨트롤러 역할
        
         - 서비스: 핵심 비즈니스 로직 구현
        
         - 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
        
         - 도메인: 비즈니스 도메인 객체 
        
                        예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨
        
    - **회원 도메인과 리포지토리 만들기**
        
        ```jsx
        public interface MemberRepository {
            Member save(Member member);                          //회원이 저장소에 저장
            Optional<Member> findById(Long id);                  //ID로 회원 찾기
            Optional<Member> findByName(String name);            //Name으로 회원 찾기
            List<Member> findAll();                              //지금까지 저장된 모든 회원 리스트 반환
        }
        ```
        
        ```jsx
        public class MemoryMemberRepository implements MemberRepository{
            private  static Map<Long,Member> store = new HashMap<>();
            private static long sequence = 0L;
        
            @Override
            public Member save(Member member) {
                member.setId(++sequence);            //고객의 ID를 시스템이 정하도록
                store.put(member.getId(), member);
                return member;
            }
            @Override
            public Optional<Member> findById(Long id) {
                return Optional.ofNullable(store.get(id));
            }
            @Override
            public Optional<Member> findByName(String name) {
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
        
    - **회원 리포지토리 테스트 케이스 작성**
        
        ```jsx
        class MemoryMemberRepositoryTest {
            MemoryMemberRepository repository = new MemoryMemberRepository();
        
            @Test
            public void save() {
                Member member = new Member();
                member.setName("spring");
                repository.save(member);
                Member result = repository.findById(member.getId()).get();
                Assertions.assertEquals(result,member);
                assertThat(member).isEqualTo(result);
            }
        }
        ```
        
        > 바꾸고자 하는 단어에서 Shift + F6하면 자동으로 아래의 것도 같이 바뀜
        > 
        
        ---
        
        <aside>
        💡 test는 순서와 관계없이 서로 의존관계가 없어야 한다.
        
        </aside>
        
        ```jsx
        public void clearStore() {
                store.clear();
            }
        ```
        
        ```jsx
        @AfterEach
            public void afterEach() {
                repository.clearStore();
            }
        ```
        
    - **회원 서비스 개발**
        
        ```jsx
        private final MemberRepository memberRepository = new MemoryMemberRepository();
        
            public Long join(Member member) {                //회원가입
                validateDuplicateMember(member);             //중복 회원 검증
                memberRepository.save(member);
                return member.getId();
            }
            private void validateDuplicateMember(Member member) {
                memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
            }
        ```
        
    - **회원 서비스 테스트**
        
        test 쉽게 만드는 법 : ctrl + shift + T하고 create 하고 밑에 검사할 것 선택
        
        > given, when, then 주석 처리하는 습관 갖기
        > 
        
        ---
        
        ```jsx
        MemberService memberService;
        MemoryMemberRepository memberRepository;
        
            @BeforeEach
            public void beforeEach() {
                memberRepository = new MemoryMemberRepository();
                memberService = new MemberService(memberRepository);
            }
        ```
        
- **👣 Section 4 - 스프링 빈과 의존관계**
    - **스프링 빈을 등록하는 2가지 방법‼️**
        - **1. 컴포넌트 스캔과 자동 의존관계 설정**
            
            ```jsx
            @Controller
            public class MemberController {
                private final MemberService memberService;
                @Autowired
                public MemberController(MemberService memberService) {
                    this.memberService = memberService;
                }
            }
            ```
            
            객체를 생성해서 넣어둔게 spring bin.( = 스프링 빈 등록)
            
            (생성하는 것은 @Component가 하는데 @Controller, @Service, @Repository 모두 포함하고 있음)
            
            비슷한 결로 @Service, @Repository도 있다.
            
            @Autowired는 dependency injection 역할을 한다. 따라서 위에서는 controller와 service를 연결해 준 것.
            
        - **2. 자바 코드로 직접 스프링 빈 등록하기**
            
            ```jsx
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
            
            MemberService가 등록이 되는데 service가 repository를 필요로 하므로 밑에서 repository 등록   → service는 repository와 엮여야하므로 return new                                                               MemberService(memberRepository());
            
    
    > 컴포넌트 스캔과 비교했을 때 직접 코드 넣으면 지금 처럼 구현 클래스 변경 시(정형화 되지 않은 클래스 설정) 마지막 return 부분만 바꿔주면 돼서 간편하다. (컴포넌트 스캔은 코드 구석구석 다 바꿔야 함)
    > 
- **👣 Section 5 - 회원 관리 예제 (웹 MVC 개발)**
    - **홈 화면 추가 (웹 기능)**
        
        ```jsx
        @Controller
        public class HomeController {
            @GetMapping("/")
            public String home() {
                return "home";
            }
        }
        ```
        
    - **등록 (웹 기능)**
        
        ```jsx
        @PostMapping("/members/new")
            public String create(MemberForm form) {
                Member member = new Member();
                member.setName(form.getName());
        
                memberService.join(member);
        
                return "redirect:/";
            }
        ```
        
        **@GetMapping :** 데이터 조회 시 (주로)
        
                                          ⇒ url 창에 바로 치는 그것
        
        **@PostMapping :** 데이터 등록 시 (주로)
        
                                          ⇒ 데이터를 form에 넣어 전달할 때
        
    - **조회 (웹 기능)**
        
        
- **👣 Section 6 - 스프링 DB 접근 기술**
    - **순수 JDBC**
        
        
    - **스프링 통합 테스트**
        
        `@SpringBootTest`
        
        → 스프링 컨테이너와 테스트를 함께 실행한다.
        
        `@Transactional`
        
        → 매 test마다 aftereach로 데이터를 초기화/삭제할 필요없이 transaction을 먼저 실행하고 난 후 테스트 후 테스트가 끝나면 알아서 롤백을 해준다.
        
        ⇒ `@AfterEach` 는 단위테스트( 스프링 컨테이너 없이 자바로만)
        
    - **스프링 JDBC Template**
        
        
    - **JPA**
        
        간단하게 JPA는 interface, Hibernate는 구현체
        
        JPA = 객체 + ORM(object,relational, mapping)
        
    - **스프링 데이터 JPA**
        
        ```jsx
        public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
            Optional<Member> findByName(String name);
        }
        ```
        
        **스프링 데이터 JPA 제공 기능**
        
        - 인터페이스를 통한 기본적인 CRUD
        - findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공
        - 페이징 기능 자동 제공
- **👣 Section 7 - AOP**
    - **AOP가 필요한 상황**
        
        여러 로직들의 시간을 측정할 때 효율적으로 할 수 있게 해주는 것
        
    - **AOP 적용**
        
        시간 측정 로직을 원하는 곳에 적용!
        
        ![Untitled](SPRING%20BOOT%2068ea037869b94df18216e34ee4e174cc/Untitled%204.png)
        
        ![Untitled](SPRING%20BOOT%2068ea037869b94df18216e34ee4e174cc/Untitled%205.png)