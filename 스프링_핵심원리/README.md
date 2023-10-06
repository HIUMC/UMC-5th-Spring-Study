# 스프링 핵심 원리

## 1. 객체 지향 설계와 스프링

- Spring Framework : Java 애플리케이션 개발을 지원하는 오픈소스 프레임워크
    - 핵심 기술 : 스프링 DI 컨네이너, AOP, 이벤트
    - 웹 기술 : 스프링 MVC, 스프링 WebFlux
    - DB 접근 기술 : 트랜잭션, JDBC, ROM 지원, XML 지원
    
- Spring Boot : Spring Framework의 기술들을 편리하게 사용하도록 도와주는 기술
    - Spring 애플리케이션 쉽게 생
    - Tomcat 웹 서버 내장 → 별도의 웹 서버 설치 x
    - 빌드 구성을 위한 starter 종속성 제공 : 종속되는 라이브러리를 자동으로 가져옴
    - 스프링, 외부 라이브러리 자동 구성 : 외부 라이브러리 최적화
    - 메트릭, 상태 확인 외부 구성 기능 제공 : 모니터링 지원
    
- 핵심 컨셉 : 객체 지향 언어의 틀징을 살려내는 프레임워크 ⇒ 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 도구

---

- 다형성 (Polymorphism) :
    - 역할과 구현을 분리
        - 클라이언트는 역할(인터페이스)만 알면 됨
        - 클라이언트는 구현 대상의 내부 구조를 알 필요 x
        - 클라이언트는 구현 대상의 내부 구조가 변경되어도 영향 x
        - 클라이언트는 구현 대상 자체를 변경해도 영향 x
        
        ⇒ 유연하고 변경이 용이하다
        
        ⇒ 여러 개의 구현 개체가 존재 가능하고 각각이 대체 가능하다. 
        
        ⇒ 각 구현체에 내부 구현이 바뀌어도 인터페이스나 다른 코드에 영향을 주지 않음 (대체 가능)
        

        
        다형성의 예시
        
        - 역할 = 인터페이스
        - 구현 = 인터페이스를 구현한 클래스, 객체
        
        ⇒ 객체를 설계할 때, 역할과 구현을 분리
        
        객체 설계시 역할(인터페이스)먼저 부여, 해당 역할을 수행하는 구현 객체 생성
        
        역할 > 구현
        
    
    - 자바 언어의 다형성 :오버라이딩 (Overriding)
        
        부모 클래스로부터 상속받은 메소드를 자식 클래스에서 재정의하여 사용
        
     
    MemberService가 바라보는걸 MemoryMemeberRepository 또는 JdbcMemberRepository로 변경 가능
    
    ⇒ 다형성으로 인터페이스를 구현한 객체를 실행 시점에 유연하게 변경
    
    - 본질 : 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경
    
    ⇒ 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경
    
    - 정리
        - 유연하고 변경 용이
        - 확장 가능한 설계 : 구현을 무한하게 확장 가
        - 클라이언트에 영향 x
    - 한계
        - 인터페이스가 변하면 클라이언트, 서버에 큰 변경이 옴
    
    ⇒ 인터페이스를 안정적으로 설계하는것이 중요
    

- 스프링과 객체 지향 : 다형성이 가장 중요
    - 스프링의 제어의 역전(IoC), 의존관계 주입(DI)은 다형성을 활용하여 역할과 구현을 실현

---

- SOLID : 좋은 객체 지향 설계의 5가지 원칙
    1. SRP : 단일 책임 원칙 (Single Responsibility Principle)
    2. OCP : 개방-폐쇄 원칙 (Open/Closed Principle)
    3. LSP : 리스코프 치환 원칙 (Liskov Substitution Principle)
    4. ISP : 인터페이스 분리 원칙 (Interface Segregation Principle)
    5. DIP : 의존관계 역전 원칙 (Dependency Inversion Principle)
    
    - SRP : 단일 책임 원칙 (Single Responsibility Principle)
        - 한 클래스는 하나의 책임만 가져야 함 (책임이 클 수도 있고 작을 수도 있음)
        - 중요한 기준은 변경 : 변경이 있을 때 클래스나 하나의 지점만 고치면 단일 책임 원칙을 따름
    
    - OCP : 개방-폐쇄 원칙 (Open/Closed Principle)
        - 소프트웨어 요소는 확장에는 열여 있으나, 변경에는 닫혀있어야함
        - 인터페이스를 구현한 새로운 클래스 하나를 만드는 것 : 확장(o), 변경(x)
        
        ```java
        public class MemberService {
        	private MemberRepository memberRepository = new MemoryMemberRepository();
        }
        ```
        
        ```java
        public class MemberService {
        //private MemberRepository memberRepository = new MemoryMemberRepository();
        	private MemberRepository memberRepository = new JdbcMemberRepository();
        }
        ```
        
        구현 객체를 변경하려면, 클라이언트 코드(인터페이스)를 변경해야함 
        
        ⇒ 다형성을 사용하였지만 OCP원칙이 지켜지지 않음
        
        ⇒ 객체를 생성하고, 연관관계를 맺어주는 별도의 설정이 필요 → 스프링 컨테이너의 역
        
    
    - LSP : 리스코프 치환 원칙 (Liskov Substitution Principle)
        - 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스를 바꿀 수 있어야함
        
        ex) 자동차 인터페이스에서 액셀은 앞으로 가 기능 하지만 뒤로 가게 구현하면 LSP를 위반
        
        느리더라도 앞으로 가야 LSP를 지킴
        
        - 하위 클래스는 인터페이스 규약을 다 지켜야 하는 것
        - 인터페이스를 구현한 구현체를 믿고 사용하기 위함
        
    - ISP : 인터페이스 분리 원칙 (Interface Segregation Principle)
        - 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 나음
        
        ex) 자동차 인터페이스 → 운전 인터페이스, 정비 인터페이스로 분리
        
        사용자 클라이언트 → 운전자 클라이언트, 정비사 클라이언트로 분리
        
        ⇒ 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향 x
        
        - 인터페이스가 명확해지고 대체 가능성 높아짐
    
    - DIP : 의존관계 역전 원칙 (Dependency Inversion Principle)
        - 구현 클래스에 의존하지 말고 인터페이스에 의존해야함 ⇒ 역할에 의존
        - 클라이언트가 인터페이스에 의존해야 객체 구현체도 유연하게 변경 가능
        
        ```java
        public class MemberService {
        	private MemberRepository memberRepository = new MemoryMemberRepository();
        }
        //MemberRepository : 인터페이스
        //MemoryMemberRepository : 구현 클래스
        ```
        
        MemberService는 MemoryMemberRepository에도 의존(해당 코드를 안다는 것)
        
        ⇒ MemberService는 MemberRepository, MemoryMemberRepository에 의존
        
        ⇒ DIP 위반 (구체화에 의존)
        

- 객체 지향의 핵심은 다형성 → 다형성 만으로는 OCP, DIP 지켜지지 않음
- 스프링 → 다형성 + OCP, DIP를 가능하도록 지원 ⇒ 클라이언트 코드 변경 없이 기능 확장 가능

---

## 2. 예제 만들기

- 회원 도메인 협력 관계


- 회원 클래스 다이어그램



- 회원 서비스 역할→ MemberService 인터페이스
- 회원 서비스 구현 → MemberServiceImpl 구현체
- 회원 저장소 역할→ MemoryRepository 인터페이스
- 회원 저장소 구현 → MemoryMemRepository or DbMemberRepository

- 회원 객체 다이어그램 : 실제 인스턴스끼리의 참조



- [Intellij]
    
    Generate 단축키 : Alt + Insert
    
    main함수 생성 : psvm
    
    변수 추출(refractor → Introduce Variable) : Ctrl + Alt + v
    
    sout : System.out.println();
    
- [Java] : enum
    
    <aside>
    
    
    </aside>
    
    ```java
    //다음과 같이 선언
    enum Gender{
    	MALE,
    	FEMALE;
    }
    
    //enum은 class와 같음 (내부적으로 생략)
    //static과 final로 상수 취급
    class Gender{
    	public static final Gender MALE = new Gender();
    	public static final Gender FEMALE = new Gender();
    }
    ```
    
- [Java] : Map
    
    <aside>
    
    
    </aside>
    
    ```java
    //다음과 같이 선언
    private static Map<Long, Member> store = new HashMap<>();
    
    //put : 값 넣기
    store.put(long, member);
    
    //값 리턴 (key값에 대응되는 value)
    store.get(long);
    ```
    
- [Java] : Long
    
    <aside>
    
    
    long : 기본 데이터 유형, 값 뒤에 L을 작성해야함
    
    </aside>
    
    ```java
    long num1 = 10000L;
    Long num2 = 10000L;
    ```
    
- [Java] : Assertions
    
    <aside>
    
    특정 조건을 검증할 때 사용
    Junit과 assertj의 라이브러리가 있는데 강의에서는 Junit 라이브러리 사용
    
    </aside>
    
    ```java
    .isGreaterThan()
    .isLessThan()
    .isEqualTo()
    ```
    
- [Java] : given-when-then
    
    <aside>
    
    
    given : 테스트를 위해 주어진 상태, 조건, 환경
    when : 테스트 대상에게 가해진 상태, 조건 , 환경
    then : 결과
    
    </aside>
    

---

## 3. 객체 지향 원리 적용



FixDiscountPolicy에서 RateDiscountPolicy로 구현체를 변경할때, OrderServiceImpl의 코드를 변경해야함 ⇒ DiscountPolicy 인터페이스 뿐 아니라 FixDiscountPolicy 구체 클래스도 함께 의존

```java
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

→ DIP 위반 : 인터페이스뿐 아니라 구현 클래스에도 의존했기 때문

→ OCP 위반 : OrderServiceImpl의 코드를 변경하였기 때문


DiscountPolicy 인터페이스 뿐 아니라
FixDiscountPolicy 구체 클래스도 함께 의존
→ DIP 위반



FixDiscountPolicy를 RateDiscountPolicy로 변경하면 OrderServiceIml의 소스코드도 변경해야함
→ OCP 위반

- 해결책 : DIP를 의반하지 않도록, 인터페이스에만 의존하도록 변경

```java
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy;
}
```

다음에서 널포인터에러가 발생하므로, 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하여 주입

- AppConfig : 애플리케이션의 전체 동작 방식을 구성(configuration)하기 위해, 구현 객체를 생성하고 연결하는 설정 클래스
    
    ⇒ 실제 동작에 필요한 구현 객체를 생성
    
    
    
    객체의 생성과 연결을 AppConfig가 담당
    
    MemberServiceImpl은 MemberRepository 인터페이스만 의존하면 됨 (구현을 몰라도 됨)
    
- 생성자 주입 (Dependecny Injection)



appConfig객체는 memoryMemberRepository 객체를 생성
해당 참조값을 memberServiceImpl에 생성자로 전달(주입)

```java
// MemberServiceImpl.java
public class MemberServiceImpl implements MemberService{

    //멤버 리포지토리 필요
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}

// AppConfig.java
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
}
```

MemberServiceImpl에서는 구체적인 인스턴스가 정해지지 않음

→ MemberRepository를 매개변수로 가지는 생성자 생성

→ AppConfig에서 MemberService를 호출하여 파라미터에 MemoryMemberRepository()를 생성

→ MemberRepository 구현체를 MemberServiceImpl에서 정하지 않고, AppConfig에서 정함

⇒ DIP 위반 x : 인터페이스에서만 의존

생성자를 통해 객체 인스턴스의 참조를 주입

MemberServiceImpl → MemoryMemberRepository

OrderServiceImpl → MemoryMemberRepository, FixDiscountPolicy

- AppConfig 리팩터링 : 중복 제거, 애플리케이션 전체 구성 파악 가능

```java
// AppConfig.java
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

		public MemberRepository memberRepository(){
				return new MemoryMemberRepository();
		}
}
```

⇒ 사용영역의 어떠한 코드도 변경할 필요 x

- SRP 단일 책임 원칙 : 클라이언트 객체는 실행하는 책임만 담당

- DIP 의존관계 역전 원칙 : AppConfig가 객체 인스턴스를 클라이언트 대신 생성하여 의존관계를 주입

- OCP : 소프트웨어 요소를 새롭게 확장 가능 & 사용 영역을 변경할 필요 없음

---

- 제어의 역전 Inversion of Control (IoC) :
    
    클라이언트 구현 객체가 필요한 객체를 생성하고 실행하는것이 아닌,
    
    제어의 흐름에 대한 권한을 AppConfig에 맡기는 방식
    
    ⇒ 제어 흐름을 직접 제어하는것이 아닌 제어권을 넘겨 외부에서 관리하는 것
    
    - 프레임워크 : 내가 작성한 코드를 제어하고 대신 실행하는 주체 ex) JUnit
    - 라이브러리 : 내가 작성한 코드가 직접 제어의 흐름을 담당하는 주체

- 의존관계 주입 Dependency Injection (DI) : 인터페이스에만 의존하도록 설계
    
    의존관계를 정적인 클래스 의존관계, 동적인 객체 의존관계로 구분
    
    - 정적인 클래스 의존관계 : 애플리케이션을 실행하지 않아도 판단 가능한 의존관계
        
        실제 어떤 객체가 주입되는지는 확인 불가능
        
    - 실행 시점에 결정되는 동적인 객체 의존관계 : 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존관계
    

⇒ 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 가능

- DI 컨테이너 (DI 컨테이너) : 객체를 생성하고 관리하며 의존관계를 연결해 주는 것
    
    (= AppConfig)
    

---

- Spring 전환
    
    
    - Configuration : 애플리케이션의 설정 정보, 구성 정보를 담당
        
        → 각 메소드에 @Bean → spring 컨테이너에 등록됨
        
        key : 메서드 이름, value : 객체 인스턴스
        
        이름 or 타입을 주고 getBean으로 반환 가능
        
    
    - ApplicationContext : 스프링 컨테이너
        
        @Configuration이 붙은 AppConfig를 구성정보로 사용
        
        → 해당 AppConfig에서 @Bean이라고 적힌 모든 메서드를 호출하여 반환한 객체를 스프링 컨테이너에 저장
        
    
    - 스프링 빈 : 스프링 컨테이너에 등록된 객체
        
        @Bean 이 붙은 메서드의 이름을 스프링 빈의 이름으로 사용
        
        key : 메서드 이름, value : 객체 인스턴스
        
        application.getBean() 메서드를 이용하여 스프링 컨테이너에 저장된 객체를 조회 가능
        
    

---

## 4. 스프링 컨테이너와 스프링 빈

- 스프링 컨테이너 생성
    
    ```java
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    //ApplicationContext : 인터페이스
    //new AnnotationConfigApplicationContext(AppConfig.class) : 인터페이스의 구현체
    ```
    
    ApplicationContext : 스프링 컨테이너 (인터페이스), 애노테이션 기반의 자바 설정 클래스로 구성 (AppConfig)
    
    1. 스프링 컨테이너 생성
    
    
    
    AppConfig.class → 구성 정보
    
    1. 스프링 빈 등록
    
    
    
    스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 스프링 빈에 등록
    
    - 빈 이름 : 메서드 이름 사용 or 직접 부여 가능 (@Bean (name=”memberService2”)
    
    1. 의존관계 주입
    
    
    

- 컨테이너의 빈 조회
    
    ```java
    public class ApplicationContextInfoTest {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
        @Test
        @DisplayName("모든 빈 출력하기")
        void findAllBean() {
            String[] beanDefinitionNames = ac.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
        }
    }
    ```
    
    - ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름 조회
    - ac.getBean(빈 이름, 빈 객체) :
    - 