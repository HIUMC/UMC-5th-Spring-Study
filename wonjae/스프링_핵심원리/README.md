# 스프링 핵심 원리

## 1. 객체 지향 설계와 스프링

- Spring Framework : Java 애플리케이션 개발을 지원하는 오픈소스 프레임워크

  - 핵심 기술 : 스프링 DI 커네이너, AOP, 이벤트
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

    ![다형성의 예시](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled.png)

    다형성의 예시

    - 역할 = 인터페이스
    - 구현 = 인터페이스를 구현한 클래스, 객체

    ⇒ 객체를 설계할 때, 역할과 구현을 분리

    객체 설계시 역할(인터페이스)먼저 부여, 해당 역할을 수행하는 구현 객체 생성

    역할 > 구현
  - 자바 언어의 다형성 :오버라이딩 (Overriding)

    부모 클래스로부터 상속받은 메소드를 자식 클래스에서 재정의하여 사용

  ![MemberService가 바라보는걸 MemoryMemeberRepository 또는 JdbcMemberRepository로 변경 가능](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%201.png)

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

![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%202.png)

- 회원 클래스 다이어그램

![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%203.png)

- 회원 서비스 역할→ MemberService 인터페이스
- 회원 서비스 구현 → MemberServiceImpl 구현체
- 회원 저장소 역할→ MemoryRepository 인터페이스
- 회원 저장소 구현 → MemoryMemRepository or DbMemberRepository
- 회원 객체 다이어그램 : 실제 인스턴스끼리의 참조

![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%204.png)

- [Intellij]

  Generate 단축키 : Alt + Insert

  main함수 생성 : psvm

  변수 추출(refractor → Introduce Variable) : Ctrl + Alt + v

  sout : System.out.println();
- [Java] : enum

  <aside>
    <img src="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C.png" alt="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C.png" width="40px" /> enum (열거형) : 클래스처럼 보이게 하는 상수

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
    <img src="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" alt="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" width="40px" /> Map : 키와 값을 하나의 쌍으로 저장하는 방식 (key, value)

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
    <img src="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" alt="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" width="40px" /> Long : long 자료형을 객체에 랩(감싸는)하는 클래스

  long : 기본 데이터 유형, 값 뒤에 L을 작성해야함

  </aside>

  ```java
  long num1 = 10000L;
  Long num2 = 10000L;
  ```
- [Java] : Assertions

  <aside>
    <img src="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" alt="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" width="40px" /> Assertions : 테스트에서 사용되는 메서드
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
    <img src="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" alt="%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C%201.png" width="40px" /> given-when-then : 테스트에서 사용되는 조건

  given : 테스트를 위해 주어진 상태, 조건, 환경
  when : 테스트 대상에게 가해진 상태, 조건 , 환경
  then : 결과

  </aside>

---

## 3. 객체 지향 원리 적용

![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%205.png)

FixDiscountPolicy에서 RateDiscountPolicy로 구현체를 변경할때, OrderServiceImpl의 코드를 변경해야함 ⇒ DiscountPolicy 인터페이스 뿐 아니라 FixDiscountPolicy 구체 클래스도 함께 의존

```java
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

→ DIP 위반 : 인터페이스뿐 아니라 구현 클래스에도 의존했기 때문

→ OCP 위반 : OrderServiceImpl의 코드를 변경하였기 때문

![DiscountPolicy 인터페이스 뿐 아니라
FixDiscountPolicy 구체 클래스도 함께 의존
→ DIP 위반](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%206.png)

DiscountPolicy 인터페이스 뿐 아니라
FixDiscountPolicy 구체 클래스도 함께 의존
→ DIP 위반

![FixDiscountPolicy를 RateDiscountPolicy로 변경하면 OrderServiceIml의 소스코드도 변경해야함
→ OCP 위반](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%207.png)

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

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%208.png)

  객체의 생성과 연결을 AppConfig가 담당

  MemberServiceImpl은 MemberRepository 인터페이스만 의존하면 됨 (구현을 몰라도 됨)
- 생성자 주입 (Dependecny Injection)

![appConfig객체는 memoryMemberRepository 객체를 생성
해당 참조값을 memberServiceImpl에 생성자로 전달(주입)](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%209.png)

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

  - 초등학생수준의 IoC : 라따뚜이

  쥐가 사람을 조종하여 사람으로 하여금 요리를 하도록 함 → 인간이 직접 생각해서 움직이는것이 아닌, 움직임의 권한을 쥐에게 맡겨 외부에서 관리

  - 프레임워크 : 내가 작성한 코드를 제어하고 대신 실행하는 주체 ex) JUnit
  - 라이브러리 : 내가 작성한 코드가 직접 제어의 흐름을 담당하는 주체
- 의존관계 주입 Dependency Injection (DI) : 인터페이스에만 의존하도록 설계

  의존관계를 정적인 클래스 의존관계, 동적인 객체 의존관계로 구분

  - 정적인 클래스 의존관계 : 애플리케이션을 실행하지 않아도 판단 가능한 의존관계

    실제 어떤 객체가 주입되는지는 확인 불가능
  - 실행 시점에 결정되는 동적인 객체 의존관계 : 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존관계

⇒ 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 가능

IoC :

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

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2010.png)

  AppConfig.class → 구성 정보

  1. 스프링 빈 등록

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2011.png)

  스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 스프링 빈에 등록

  - 빈 이름 : 메서드 이름 사용 or 직접 부여 가능 (@Bean (name=”memberService2”)

  1. 의존관계 주입

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2012.png)
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
- 스프링 빈 조회 - 동일한 타입이 둘 이상일때 → 오류 발생 ⇒ 빈 이름을 지정해야함

  ac.getBeansOfType() : 해당 타입의 모든 빈 조회 가능
- 스프링 빈 조회 - 상속관계 : 부모 타입으로 조회하면, 자식 타입도 함께 조회됨

  Object 타입으로 조회하면, 모든 스프링 빈을 조회하게 됨
- BeanFactory : 스프링 컨테이너의 최상위 인터페이스

  - 스프링 빈을 관리하고 제공
  - getBean() 제공
- ApplicationContext : BeanFactory의 기능을 모두 상속받아 제공

![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2013.png)

- BeanFactory와 ApplicationContext의 차이점

  - ApplicationContext는 다음과 같이 여러개의 여러개의 부가기능 제공

    ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2014.png)
  - MessageSource : 국제화 기능
  - EnvironmentCapable : 로컬, 개발, 운영을 구분해서 처리
  - ApplicaitonEventPublisher : 이벤트 발행, 모델을 편리하게 지원
  - ResourceLoader : 파일, 클래스패스, 리소스를 조회

  ⇒ ApplicationContext만 사용
- 설정 지원 형식 : XML

  - 스프링 컨테이너는 다양한 형식의 정보를 받아들일 수 있음

  ex) 자바 코드, SML, Groovy

![AppConfig.class (AnnotationConfigApplicationContext) : 자바클래스로 생성
appConfig.xml (GenericXmlApplicationContext) : xml문서로 설정정보 사용](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2015.png)

AppConfig.class (AnnotationConfigApplicationContext) : 자바클래스로 생성
appConfig.xml (GenericXmlApplicationContext) : xml문서로 설정정보 사용

- Annotation 기반 자바 코드 설정 사용

```java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
```

- XML 설정 사용 : 컴파일 없이 빈 설정 정보를 변경 가능

  - GenericXmlApplicationContext 사용하여 xml 설정파일 넘김

  <XML>

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

      <bean id="memberService" class="hello.core.member.MemberServiceImpl">
          <constructor-arg name="memberRepository" ref="memberRepository" />
      </bean>

      <bean id="memberRepository" class="hello.core.member.MemoryMemberRepository" />

      <bean id="orderService" class="hello.core.order.OrderServiceImpl">
          <constructor-arg name="memberRepository" ref="memberRepository"/>
          <constructor-arg name="discountPolicy" ref="discountPolicy"/>
      </bean>

      <bean id="discountPolicy" class="hello.core.discount.RateDiscountPolicy"/>

  </beans>
  ```

  <Java class>

  ```java
  @Configuration
  public class AppConfig {

      @Bean
      public MemberService memberService() {
          return new MemberServiceImpl(memberRepository());
      }

      @Bean
      public MemberRepository memberRepository() {
          return new MemoryMemberRepository();
      }

      @Bean
      public OrderService orderService() {
          return new OrderServiceImpl(memberRepository(), discountPolicy());
      }

      @Bean
      public DiscountPolicy discountPolicy() {
  //        return new FixDiscountPolicy();
          return new RateDiscountPolicy();
      }
  }
  ```

  ⇒ XML에서 빈과 1대1 매치됨 (같은 코드)
- 역할과 구현을 나눠 다양한 설정 형식을 지원

  - BeanDefinition으로 추상화

![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2016.png)

- BeanDefinition : 빈 설정 메타 정보

  - @Bean, `<bean>` 당 하나의 메타 정보 생성

  ⇒ 해당 메타 정보를 기반으로 스프링 빈 생성

  - 설정정보를 읽어들여  BeanDefinition 생성
- Spring Bean 등록

  1. 직접적으로 등록
  2. FactoryBean으로 등록 → java.config가 해당

## 5. 싱글톤 컨테이너

- 웹 어플리케이션은 여려 클라이언트가 동시에 요청

  한 클라이언트가 요청 → DI 컨테이너에서 객체를 new로 생성하여 반환

  ⇒ 여러 클라이언트가 요청 → 요청한 수만큼의 객체가 생성됨
- DI 컨테이너인 AppConfig는 요청할때마다 객체를 새로 생성 ⇒ 메모리 낭비가 심함

  ⇒ 객체를 하나만 생성하고 공유하도록 설계해야함
- 싱글톤 패턴 : 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴

  - private 생성자를 사용해 외부에서 new 키워드를 사용하지 못하도록 해야함

  ⇒ 클라이언트가 요청할 때 마다 객체를  생성하는 것이 아닌, 이미 만들어진 객체를 재사용 ⇒ 성능 향상

  <객체를 미리 생성해두는 가장 안전한 방법>

  ```java
  package hello.core.singleton;

  public class SingletonService {

      // 1. static으로 객체를 생성
      private static final SingletonService instance = new SingletonService();

      // 2. getInstance() 메서드를 통해서만 조회
      public static SingletonService getInstance(){
          return instance;
      }

      // 생성자를 private로 선언함으로써 외부에서
  		// new로 인스턴스가 생성되는 것을 방지
      private SingletonService(){

      }

      public void logic(){
          System.out.println("싱글톤 객체 로직 호출");
      }
  }
  ```

  1. static으로 객체를 생성
  2. getInstance() 메서드를 통해서만 조회, 항상 같은 인스턴스를 반환
  3. 생성자를 private로 선언함으로써 외부에서 new로 인스턴스가 생성되는 것을 방지

  - 문제점 :
    1. 싱글톤 패턴을 구현하는 코드 필요
    2. 의존관계상 getInstance()를 불러와함 → 구현에 의존 → DIP & OCP 위반
    3. 테스트하기 어려움
    4. 내부속성 변경 or 초기화 어려움
    5. private 생성자를 사용하여 자식 클래스 만들기 어려움
    6. 유연성 떨어짐
- 싱글톤 컨테이너 :

  - 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리 ⇒ 싱글톤 패턴의 문제를 해결
  - 싱글톤 레지스트리 : 싱글톤 객체를 생성하고 관리하는 기능
- 싱글톤 방식의 주의점 : 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 상태를 유지하게 설계해서는 안됨 → 무상태 설계

  - 특정 클라리언트가 값을 바꾸게 두면 안됨 (의존해서는 안됨)
  - 읽기만 가능하도록
  - 지역변수, 파라미터 등을 사용해야함
- @Configuration

  ```java
  @Configuration
  public class AppConfig {

      @Bean
      public MemberService memberService() {
          return new MemberServiceImpl(memberRepository());
      }

      @Bean
      public MemberRepository memberRepository() {
          return new MemoryMemberRepository();
      }

      @Bean
      public OrderService orderService() {
          return new OrderServiceImpl(memberRepository(), discountPolicy());
      }
  }
  ```

  @Bean memberService → new MemoryMemberRepository()

  @Bean orderService → new MemoryMemberRepository()

  각각 2개의 MemoryMemberRepository가 생성되어 스프링이 깨지는것처럼 보임

  ⇒ 결론은 같은 인스턴스

  ```java
  void configurationTest(){
          AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

          MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
          OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
          MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

          MemberRepository memberRepository1 = memberService.getMemberRepository();
          MemberRepository memberRepository2 = orderService.getMemberRepository();

          Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
          Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
      }
  ```

  memberRepository 인스턴스는 모두 같은 인스턴스 공유

  이 코드에서 memberRepository가 3번 호출되어야하지만 실제로는 한번만 호출
- @Configuration & 바이트조작

  AppConfig를 스프링 빈으로 등록하고 클래스 정보를 출력

  → bean = class hello.core.AppConfig

  $$
  SpringCGLIB
  $$

  0

  다음에서 “$SpringCGLIB$$0”는, CGLIB라는 바이트코드 조작 라이브러리를 사용해 AppConfig를 상속받은 임의의 클래스를 생성하고 해당 클래스를 스프링 빈으로 등록

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2017.png)

  임의의 다른 클래스가 싱글톤이 되도록 보장


  ```java
  @Bean
  public MemberRepository memberRepository(){
  	if (memoryMemberRepository가 스프링 컨테이너에 등록되어 있으면) {
  		return 스프링 컨테이너에서 찾아서 반환;
  	} else {
  		기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록;
  		return 반환;
  	}
  }
  ```

  AppConfig@CGLIB에서, @Bean이 붙은 메서드마다

  스프링 빈이 존재하면, 존해하는 빈을 반환

  스프링 빈이 없으면, 생성하고 스프링 빈으로 등록하고 반환

  @Configuration을 제거하면 AppConfig가 CGLIB기술 없이 스프링 빈에 등록

  다 다른 인스턴스를 가짐 ⇒ 싱글톤 깨짐

  @Bean만 사용해도 스프링 빈으로 등록, 싱글톤 깨짐

  의존관계 주입이 필요해 메서드를 직접 호출할 때 싱글톤 깨짐

## 6. 컴포넌트 스캔

- 컴포넌트 스캔 : 설정정보(AppConfig.java, XML)가 없어도 자동으로 스프링 빈을 등록하는 기능

  ```java
  @Configuration
  @ComponentScan(
          //빼고싶은 class 지정
          //직접 스프링 빈에 등록하기 위해 만들어 두었던 AppConfig을 제외
          excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
  )
  public class AutoAppConfig {

  }
  ```
  - @ComponentScan을 설정정보에 붙여줘야함 →
  - @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록

    (@Configuration도 @Component를 가지고 있음)
  - @AutoWired : 자동으로 의존관계 주입

    생성자에 붙여줌 → 해당 타입에 맞는 클래스를 찾아와 의존관계를 자동으로 연결하여 주입

  ⇒ 설정정보 자체가 사라지므로, 의존관계 주입을 클래스 내에서 해결

  1. ComponentScan

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2018.png)

  @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록

  빈 이름 : 클래스명을 사용하되 맨 앞글자만 소문자

  직접 지정하고 싶으면 @Component(”memberService”)

  1. Autowired 의존관계 주입

  ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20d2ff98c424b24598935129a0fd9a818d/Untitled%2019.png)

  생성자에 @Autowired를 지정하면 각 파라미터의 타입으로 스프링 빈을 찾아 주입

  (getBean(MemberRepository.class)와 같은 맥락)
- 컴포넌트 스캔 시 탐색할 패키지와 시작 위치 지정

  모든 자바 클래스를 스캔하면 오래걸림 → 시작위치를 지정 가능

  패키지를 지정하지 않는다면, @ComponentScan이 붙은 클래스의 패키지가 시작 위치가 됨

  ⇒ 설정 정보 클래스위 위치를 프로젝트 최상단에 두는것을 추천 ⇒ 프로젝트 하위 폴더가 컴포넌트 스캔의 대상

  - basePackages : 탐색할 패키지의 위치 지정 (해당 패키지를 포함한 하위 패키지)

    basePackages = {”hello.core”, “hello.service”}와 같이 여러개 지정 가능
  - basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정
- 컴포넌트 스캔 기본 대상

  - @Component : 컴포넌트 스캔에서 사용
  - @Controller : 스프링 MVC 컨트롤러에서 인식 & 사용
  - @Service : 스프링 비즈니스 로직에서 사용
  - @Repository : 스프링 데이터 접근 계층에서 사용
  - @Configuration : 설정 정보에서 사용 & 스프링 빈이 싱글톤을 유지하도록 처리

  ⇒ 각 애노테이션은 @Component를 가지고 있음
- 필터 :

  - includeFilters : 컴포넌트 스캔 대상을 추가로 지정
  - excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정

  ```java
  @Configuration
      @ComponentScan(
              //BeanA는 스프링 빈에 등록, BeanB는 스프링 빈에 등록 x
              includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
              excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = MyExcludeComponent.class)
      )
      static class ComponentFilterAppConfig{

      }
  ```
- FilterType

  - ANNOTATION (기본값) : 애노테이션을 인식해서 동작
  - ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작
  - REGEX : 정규 표현식
  - CUSTOM : TypeFilter라는 인터페이스를 구현해서 동작
- 중복 등록과 충돌 : 컴포넌트 스캔에서 같은 빈 이름을 등록했을때

  자동 빈 등록 → @Component, @Autowired

  수동 빈 등록 → @Bean

  1. 자동 빈 등록간의 충돌

  이름이 같은 경우 오류를 발생 → ConfilctingBeanDefinitionException 예외 발생

  1. 수동 빈 등록, 자동 빈 등록간의 충돌

  수동으로 등록한 빈이 자동으로 등록한 빈을 오버라이딩함
