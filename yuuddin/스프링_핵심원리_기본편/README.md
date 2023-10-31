# SPRING 핵심 원리 - 기본

<aside>
💡 스프링 핵심 원리 - 기본편

</aside>

- **짜잘 개념들!**
    
    ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled.png)
    
    > `new Member(1L, "memberA", Grade.*VIP*);` 를 변수에 저장하고 싶을 땐 ctrl+Alt+v 하면     `Member member = new Member(1L, "memberA", Grade.VIP);` 자동 생성
    > 
    
    > psvm : `public static void main(String[] args) { }`
    > 
    
    > soutv : `System.*out*.println(메소드 내 사용 가능 변수)`
    > 
    
    > soutm: `System.*out*.println(메소드명)`
    > 
    
    > iter : list나 배열이 있을 때 for문으로 돌려주는 구문 자동완성
    > 
    
    > ctrl+D : 선택한 문장(들) 복제
    > 
    
    ```jsx
    //then
            Assertions.assertThat(findMember).isEqualTo(member);
    ```
    
    > *`assertThat*(object1).isSameAs(object2);`  는 참조어 비교 (==)
    *`assertThat*(object1).isEqualTo(object2);` 는 java의 equals
    > 
- **DIP 지키기**
    
    `private final DiscountPolicy discountPolicy = new FixDiscountPolicy();` DIP 위반
    
                → DiscountPolicy(인터페이스)도 의존하고 FixDiscountPolicy(구현체)도 의존
    
    ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%201.png)
    
    ---
    
    <aside>
    💡 인터페이스만 의존하도록 해결하는 방법
    
    </aside>
    
    ```jsx
    private final MemberRepository memberRepository;
    
        public MemberServiveImpl(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    ```
    
    ```jsx
    public class AppConfig {
    
        public MemberService memberService() {
            return new MemberServiveImpl(memberRepository());
        }
    
        private MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    
        public OrderService orderService() {
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }
    
        private DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }
    ```
    
    ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%202.png)
    
    따라서 사용 영역을 구현할 때는 기능만 관여를 하고 구성 영역은 따로 빼내서 두 역할을 분리
    
- **스프링 빈 조회**
    - 스프링 빈 등록
        
        ```jsx
        @Configuration
        public class AppConfig {
        
            @Bean
            public MemberService memberService() {
                return new MemberServiveImpl(memberRepository());
            }
        ```
        
        ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%203.png)
        
    - 상속 관계의 경우
        
        부모 타입으로 조회하면 자식 타입이 줄줄줄 끌려 나온다
        
    
- **ApplicationContext**
    
    ```jsx
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    ```
    
    - ApplicationContext는 BeanFactory의 기능을 상속 받는다.
    - ApplicationContext는 BeanFactory 기능 외에 여러 부가 기능을 추가로 제공한다.
    - ApplicationContext와 BeanFactory를 **스프링 컨테이너**라고 한다.
    
    ---
    
    ```jsx
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    ```
    
    ```jsx
    <bean id="memberRepository"
              class="hello.core.member.MemoryMemberRepository" />
    ```
    
    ---
    
    - spring은 Bean Definition이라는 것으로 Spring Bean의 설정 메타 정보를 추상화한다
- **싱글톤 컨테이너**
    
    ```jsx
    @Bean
        public MemberService memberService() {
            return new MemberServiveImpl(memberRepository());
        }
    ```
    
    ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%204.png)
    
    ---
    
    ### SINGLETON 구현
    
    ```jsx
    private static final SingleTonService instance = new SingleTonService();       
    
    public static SingleTonService getInstance() {
        return instance;
    }
    ```
    
    - static영역에 SingleTonService를 참조하는 instance를 미리 생성해서 올려둔다.
    - 이는 오직 getInstance()를 통해서만 조회 가능하다
    - private으로 설정해 외부에서 new로 객체가 설정되는 것을 막는다.
    
    문제점들
    
    - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
    - 의존관계상 클라이언트가 구체 클래스에 의존한다. → DIP를 위반한다.
    - 클라이언트가 구체 클래스에 의존한다. → OCP 원칙을 위반할 가능성이 높다.
    - 유연성이 떨어져 테스트하기 어렵다.
    - private 생성자로 자식 클래스를 만들기 어렵고 내부 속성을 변경하거나 초기화 하기 어렵다.
    
    ---
    
    ### SINGLETON CONTAINER
    
    ```jsx
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
            MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    ```
    
    이는 기존의 문제점들을 모두 해결하는 동시에 싱글톤 기능을 제공한다. 
    
    ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%205.png)
    
    ---
    
    ### SINGLETON 주의점
    
    싱글톤 객체는 무상태여야 한다. (⇒ 클라이언트가 값을 변경할 수 있으면 안됨)
    
    ```jsx
    public class StatefulService {
        //private int price;    //상태를 유지하는 필드
    
        public int order (String name, int price) {
            System.out.println("name = " + name + "price = " + price);
            //this.price = price;     //여기가 문제!
            return price;
        }
    }
    ```
    
    ---
    
    ### @Configuration
    
    ```jsx
    MemberServiveImpl memberService = ac.getBean("memberService", MemberServiveImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
    ```
    
    ---
    
    <aside>
    💡 **@Configuration이 1번만 호출하도록 한다!**
    
    </aside>
    
    ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%206.png)
    
    내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.
    
    ```jsx
    @Bean
    public MemberRepository memberRepository() {
     if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
    	 return 스프링 컨테이너에서 찾아서 반환;
     } else { //스프링 컨테이너에 없으면 기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
    	 return 반환
     }
    }
    ```
    
- **컴포넌트 스캔**
    
    ```jsx
    @Autowired
        public MemberServiveImpl(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    ```
    
    ---
    
    ### 권장하는 스캔 범위 지정 방법
    
    프로젝트 시작 루트에 AppConfig 같은 메인 설정 정보를 두고 @ComponentScan를 붙이고, basePackages 지정은 생략하는 방법
    
                                                                   or
    
    스프링 부트의 대표 시작 정보인 @SpringBootApplication를 프로젝트 시작 루트 위치에 두는 것이 관례
    
    ---
    
    ### 빈 등록의 중복
    
    - 자동 빈 등록과 자동 빈 등록의 이름이 같을 때
        
        자동으로 ConflictingBeanDefinitionException 예외 발생
        
    - 자동 빈 등록과 수동 빈 등록의 이름이 같을 때
        
        수동 등록된 빈이 우선권을 가져 자동 등록 빈을 override한다.
        
        하지만 만약 `@SpringBootApplication` 을 사용한다면 알아서 예외를 발생시킨다
        
    
- **의존관계 자동 주입**
    - **의존관계 주입하는 4가지 방법**
        - ⭐**생성자 주입**⭐
            
            ```jsx
            @Component
            public class OrderServiceImpl implements OrderService {
            		private final MemberRepository memberRepository;
                private final DiscountPolicy discountPolicy;
            
                @Autowired
                public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
                    this.memberRepository = memberRepository;
                    this.discountPolicy = discountPolicy; }
            ```
            
            생성자 호출 시 한번만 호출되며 **불변, 필수(null X)** 의존관계를 사용해야할 때 유용하다
            
            생성자 주입을 사용하면 **final** 키워드를 사용할 수 있다.
            
                  → final 키워드를 사용하면 생성자에서 값을 넣어주지 않는 오류를 잡아줄 수 있다
            
        - 수정자 주입 (setter 주입)
            
            ```jsx
            @Autowired
            public void setMemberRepository(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
            }
            ```
            
            **선택, 변경** 가능성이 있는 의존 관계에 사용하기 좋다
            
            @Autowired(required = false)로 수정하면 값이 들어오지 않아도 동작하게 한다
            
        - 필드 주입
            
            ```jsx
            @Autowired private MemberRepository memberRepository;
            ```
            
            DI framework가 없으면 아무것도 할 수 없음
            
            간결해서 좋아보이지만 **쓰지 말자!!!**
            
        - 일반 메소드 주입
            
            일반 메소드 위에 @Autowired를 붙이는데 거의 사용하지 않는다
            
        
    - **옵션 처리**
        
        스프링 빈이 없어도 동작해야할 때 옵션 처리 방법을 사용한다
        
        - `@Autowired(required = false)`
            
                                                      : 자동 주입할 대상이 없으면 메서드 자체가 호출 안됨
            
        - `@Autowired public void setNoBean2(@Nullable Member member)`
            
                                                      : 자동 주입할 대상이 없으면 null이 입력
            
        - `@Autowired public void setNoBean3(Optional<Member> member)`
            
                                                      : 자동 주입할 대상이 없으면 Optional.empty 가 입력
            
        
    - **롬복 (Lombok)**
        
        롬복 라이브러리에서 제공하는 다양한 어노테이션을 통해 편리하게 코드를 짤 수 있다
        
        `@RequiredArgsConstructor` 를 사용하면 final이 붙은 필드에게 값을 넣어줄 수 있도록 하는 생성자를 자동으로 짜준다
        
        ---
        
        따라서 비교해보면,
        
        ```jsx
        @Component
        public class OrderServiceImpl implements OrderService {
        		private final MemberRepository memberRepository;
            private final DiscountPolicy discountPolicy;
        
            @Autowired
            public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
                this.memberRepository = memberRepository;
                this.discountPolicy = discountPolicy; }
        ```
        
        ```jsx
        @Component
        @RequiredArgsConstructor
        public class OrderServiceImpl implements OrderService{
            private final MemberRepository memberRepository;
            private final DiscountPolicy discountPolicy;
        ```
        
        이렇게 코드가 단축된다
        
    - **조회한 빈이 2개 이상**
        - 문제 상황
            
            
            ```jsx
            @Component
            public class FixDiscountPolicy implements DiscountPolicy {
            ```
            
            ```jsx
            @Component
            public class RateDiscountPolicy implements DiscountPolicy{
            ```
            
            이렇게 DiscountPolicy 안에 2개의 빈이 등록되어 있다면
            
            ```jsx
            @Autowired
            private DiscountPolicy discountPolicy
            ```
            
            를 실행할 때 DiscountPolicy가 한개의 bean을 찾지 못하는 오류가 나게 된다
            
        - 해결 방법
            - @Autowired 필드 명 매칭
                
                ```jsx
                @Autowired
                    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy)
                ```
                
                이와 같이 rateDiscountPolicy로 필드 명을 빈 이름으로 바꾸면 이름이 같은 빈을 찾아간다
                
            - @Qualifier과 @Quilifier끼리 매칭
                
                
                ```jsx
                @Qualifier("FixDiscountPolicy")
                public class FixDiscountPolicy implements DiscountPolicy {
                ```
                
                ```jsx
                @Qualifier("mainDiscountPolicy")
                public class RateDiscountPolicy implements DiscountPolicy{
                ```
                
                위와 같이 필드에 이름을 등록해주고
                
                ```jsx
                public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
                ```
                
                사용할 때 @Qualifier를 붙여주면 같은 이름끼리 매핑이 된다
                
            - @Primary 사용
                
                ```jsx
                @Primary
                public class RateDiscountPolicy implements DiscountPolicy{
                ```
                
                fix와 rate중 rate를 우선순위로 잡히며 의존관계가 주입이 된다
                
            
            <aside>
            💡 @Primary를 주로 main 코드에, @Qualifier를 주로 서브 코드에 활용한다
            
            </aside>
            
    - **어노테이션 만들기**
        
        ```jsx
        @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
                ElementType.TYPE, ElementType.ANNOTATION_TYPE})
        @Retention(RetentionPolicy.RUNTIME)
        @Inherited
        @Documented
        @Qualifier("mainDiscountPolicy")                    //구현하고자하는 기능
        public @interface MainDiscountPolicy {         
        }
        ```
        
    - **조회한 빈이 여러개/모두 필요한 경우**
        
        ex → 고객이 fix, rate 선택하여 할인받을 수 있는 경우
        
        ```jsx
        static class DiscountService {
                private final Map<String, DiscountPolicy> policyMap;
                private final List<DiscountPolicy> policies;
        
                @Autowired
                public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
                    this.policyMap = policyMap;
                    this.policies = policies;
                    System.out.println("policyMap = " + policyMap);
                    System.out.println("policies = " + policies);
                }
        
                public int discount(Member member, int price, String discountCode) {
                    DiscountPolicy discountPolicy = policyMap.get(discountCode);
                    System.out.println("discountCode = " + discountCode);
                    System.out.println("discountPolicy = " + discountPolicy);
                    return discountPolicy.discount(member, price);
                }
            }
        ```
        
        위 코드와 같이 필요한 빈을 Map에 모두 등록한 후 호출할 때마다 Map에서 꺼내서 쓴다
        
    
- **빈 생명주기 콜백**
    
    > 스프링 빈은 객체를 생성한 후에 의존관계를 주입한다!
    > 
    
    ! 스프링 빈의 이벤트 순서 !
    
    스프링 컨테이너 생성 → 스프링 빈 생성 → 의존 관계 주입 → (완료 의미의 콜백) → 초기화 등의 작업 → (소멸 의미의 콜백) → 소멸
    
            ⇒ 생성 작업 (light & important) 과  초기화 (heavy) 의 작업을 분리하는게 좋다
    
    ---
    
    콜백 기능 구현 방법
    
    1. 인터페이스 
        
        ```java
        public class NetworkClient implements InitializingBean, DisposableBean {}
        @Override
            public void destroy() throws Exception {
                disconnect();
            }
        
            @Override
            public void afterPropertiesSet() throws Exception {
                connect();
                call("초기화 연결 메시지");
            }
        ```
        
    2. 메서드
        
        ```java
        @Bean(initMethod = "init", destroyMethod = "close")
        ```
        
    3. 어노테이션 
        
        ```java
        @PostConstruct
            public void init()
        
        @PreDestroy
            public void close()
        ```
        
        <aside>
        💡 @PostConstruct와 @PreDestroy를 사용하되 외부를 고쳐야 하면 @Bean을 사용
        
        </aside>
        
    
- **빈 스코프**
    - 스코프의 종류
        - 싱글톤: 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프 (default)
            
            ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%207.png)
            
        - 프로토타입 : 스프링 컨테이너가 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프
            
            ![프로토타입은 빈을 생성하고 의존관게를 주입하고 초기화까지만 처리해준다
            ⇒ 그 이후는 클라이언트가 책임을 가지며 @Predestroy같은 메서드 사용 X](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%208.png)
            
            프로토타입은 빈을 생성하고 의존관게를 주입하고 초기화까지만 처리해준다
            ⇒ 그 이후는 클라이언트가 책임을 가지며 @Predestroy같은 메서드 사용 X
            
        - request : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
            
            ![이렇게 동시에 여러 요청이 오면 어떤 요청이 남긴 로그인지 구분하기 어렵다 → request scope 사용](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%209.png)
            
            이렇게 동시에 여러 요청이 오면 어떤 요청이 남긴 로그인지 구분하기 어렵다 → request scope 사용
            
            - ObjectProvider를 사용
                
                ```java
                @RequiredArgsConstructor
                public class LogDemoController {....
                	private final ObjectProvider<MyLogger> myLoggerProvider;...
                	MyLogger myLogger = myLoggerProvider.getObject(); ...}
                ```
                
                - ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
                - ObjectProvider.getObject() 를 호출하시는 시점에는 HTTP 요청이 진행중이므로 request scope 빈의 생성이 정상 처리된다.
                - ObjectProvider.getObject() 를 LogDemoController , LogDemoService 에서 각각 한번씩 따로 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환된다
                
            
            ---
            
            - 프록시 사용
                
                ```java
                @Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
                ```
                
                - 실행하면 MyLogger를 상속받은 가짜 프록시 클래스를 만들어서 컨테이너에 “myLogger”라는 이름으로 객체를 등록하고 의존관계도 주입된다.
                - 가짜 프록시 객체는 request 스코프의 진짜 myLogger.log() 를 호출한다.
                - 클라이언트가 myLogger.log() 을 호출하면 사실은 가짜 프록시 객체의 메서드를 호출한 것이다.
                - 가짜 프록시 객체는 원본 클래스를 상속 받아서 만들어졌기 때문에 이 객체를 사용하는 클라이언트 입장에서는 사실 원본인지 아닌지도 모르게, 동일하게 사용할 수 있다(다형성)
                
    
    ---
    
    - 프로토타입과 싱글톤을 같이 사용할때
        - 문제점
            
            
            ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%2010.png)
            
            ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%2011.png)
            
            ![Untitled](SPRING%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20d90f0fc5692b4d959e36d1967629ada4/Untitled%2012.png)
            
            같은 빈을 사용할거라면 싱글톤으로 하지 프로토타입으로 할 이유가 없음 ⇒ 오류라고 가정
            
            싱글톤 빈 안에 생성된 프로토타입 빈은 이전에 이미 주입이 완료된 빈으로 사용할때마다 새로 생성되지 않는다
            
        - 해결 방법
            
            DI를 기다리는게 아니라 의존관계를 탐색하기 위해 빈을 컨테이너에서 찾아주는 DL (Dependency Lookup) 기능을 이용한다.
            
            - Object Provider
                
                ```java
                @Autowired
                        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
                public int logic() {
                            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();}
                ```
                
            - javax.inject.Provider
                
                ```java
                @Autowired
                        private Provider<PrototypeBean> provider;
                public int logic() {
                            PrototypeBean prototypeBean = provider.get();}
                ```