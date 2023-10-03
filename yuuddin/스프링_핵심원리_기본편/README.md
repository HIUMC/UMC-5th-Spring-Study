# SPRING BOOT

<aside>
💡 스프링 핵심 원리 - 기본편

</aside>

- 짜잘 개념들!
    
    ![Untitled](SPRING%20BOOT%20d90f0fc5692b4d959e36d1967629ada4/Untitled.png)
    
    > `new Member(1L, "memberA", Grade.*VIP*);` 를 변수에 저장하고 싶을 땐 ctrl+Alt+v 하면     `Member member = new Member(1L, "memberA", Grade.` 자동 생성
    > 
    
    > psvm : `public static void main(String[] args) { }`
    > 
    
    > soutv : `System.*out*.println( )`
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
- DIP 지키기
    
    `private final DiscountPolicy discountPolicy = new FixDiscountPolicy();` DIP 위반
    
                → DiscountPolicy(인터페이스)도 의존하고 FixDiscountPolicy(구현체)도 의존
    
    ![Untitled](SPRING%20BOOT%20d90f0fc5692b4d959e36d1967629ada4/Untitled%201.png)
    
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
    
    ![Untitled](SPRING%20BOOT%20d90f0fc5692b4d959e36d1967629ada4/Untitled%202.png)
    
    따라서 사용 영역을 구현할 때는 기능만 관여를 하고 구성 영역은 따로 빼내서 두 역할을 분리
    
- 스프링 빈 조회
    - 스프링 빈 등록
        
        ```jsx
        @Configuration
        public class AppConfig {
        
            @Bean
            public MemberService memberService() {
                return new MemberServiveImpl(memberRepository());
            }
        ```
        
        ![Untitled](SPRING%20BOOT%20d90f0fc5692b4d959e36d1967629ada4/Untitled%203.png)
        
    - 상속 관계의 경우
        
        부모 타입으로 조회하면 자식 타입이 줄줄줄 끌려 나온다
        
    
- ApplicationContext
    
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
    - 
- 싱글톤 컨테이너
    
    ```jsx
    @Bean
        public MemberService memberService() {
            return new MemberServiveImpl(memberRepository());
        }
    ```
    
    ![Untitled](SPRING%20BOOT%20d90f0fc5692b4d959e36d1967629ada4/Untitled%204.png)
    
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
    - private으로 설정해 이부에서 new로 객체가 설정되는 것을 막는다.
    
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
    
    ![Untitled](SPRING%20BOOT%20d90f0fc5692b4d959e36d1967629ada4/Untitled%205.png)