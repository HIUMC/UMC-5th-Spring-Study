# 스프링 핵심 원리 - 기본편

- **섹션 1. 객체 지향 설계와 스프링**
    - 스프링을 왜 만들었을까?
        
        자바 언어 기반의 프레임워크
        
        = 객체지향언어의 특성을 살려내는 프레임워크
        
    - 좋은 객체지향 프로그래밍
        - 객체지향 특징 : 추상화, 캡슐화, 상속, 다형
        - 컴포넌트를 쉽고 유연하게 변경하면서 개발하는 방법 → 다형성
        - 역할과 구현 → 클라이언트에 영향을 주지 않고(클라이언트는 인터페이스만 알면 됨, 내부 구조 몰라도 됨) 새로운 기능 제공 가능
        - 역할 = 인터페이스 / 구현 = 구현 클래스, 객체
        - 스프링은 다형성 극대화
        
    - 좋은 객체지향 설계의 5가지 원칙(SOLID)
        - SRP (Single responsibility principle) : 한 클래스는 하나의 책임만(변경)
        - OCP (Open/closed principle) : 확장에는 열려있으나, 변경에는 닫혀있게 → 다형성 활용 / 인터페이스 구현한 새로운 클래스 만들어서 새로운 기능 구현 (OCP는 지킬 수 없음ㅜㅜ → 스프링 컨테이너, DI 등 필요)
        - LSP (Liskov substitution principle / 리스코프 치환 원칙) : 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 됨
        - ISP (Interface segregation principle) : 특정 클라이언트를 위한 **인터페이스 여러개**가 **범용 인터페이스 하나**보다 **낫다**!
        - DIP (Dependency inversion principle / 의존관계 역전 원칙) : 인터페이스에 의존, 구현 클래스 의존 X
        - 다형성 만으로는 OCP, DIP 지킬 수 X

- **섹션 2. 스프링 핵심 원리 이해1 - 예제 만들기**
    
    회원 서비스: MemberServiceImpl
    
    - Map & HashMap
        - Map : 리스트나 배열처럼 순차적으로 해당 요소 값을 구하지 않고 key를 통해 value를 얻는다.
        - HashMap : Map 인터페이스를 상속하고 있기에 Map의 성질을 그대로 가지고 있고, Map은 키와 값으로 구성된 Entry객체를 저장하는 구조를 가지고 있는 자료구조이다.
            
            ![images_mon99745_post_fca77a41-14ea-4377-b734-afa098c5cb6d_image.png](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/images_mon99745_post_fca77a41-14ea-4377-b734-afa098c5cb6d_image.png)
            
    - soutv : System.out.println(“어쩌구 = “ + 어쩌구);
    - 주문 할인 도메인 설계
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled.png)
        

- **섹션 3. 스프링 핵심 원리 이해2 - 객체 지향 원리 적용**
    - **ctrl + shift + T : Test 만들어주기!**
    - 문제점
        - 할인 정책을 변경 시 클라이언트인 OrderServiceImpl 코드 수정 필요 private final DiscountPolicy discountPolicy = new **RateDiscountPolicy();**
        - DIP 위반 : OrderServiceImpl는 추상 클래스 뿐 아니라 **구현 클래스도! 의존**
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%201.png)
        
        - OCP 위반 : OrderServiceImpl 코드를 변경하는 순간 위반
    - 해결방안
        - DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경 필요
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%202.png)
        
        - 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다
        
    - **관심사의 분리!**
        
        ★AppConfig 등장★
        
        - 구현 객체를 생성하고 연결하는 책임을 가지는 별로의 클래스
        - **생성자 주입!**
        - 애플리케이션 실제 동작에 필요한 **구현 객체 생성**
        - public OrderService orderService(){
            
                return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy())
            
            }
            
            ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%203.png)
            
            ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%204.png)
            
        - DIP 완성!
    - AppConfig 리팩토링
        
        역할과 구현 한번에 볼 수 있게
        
        ex)
        
        public MemberService memberService(){
        
            return new MemberServiceImpl(*memberRepository*());
        
        }
        
        private static MemoryMemberRepository memberRepository() {
        
            return new MemoryMemberRepository();
        
        }
        
    - 구성 영역의 코드는 수정하되, 사용자 영역은 건들지 않고 개발 가능
    
    - IoC, DI, 컨테이너
        - 제어의 역전 IoC(Inversion of Control) : 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것
        - 의존관계 주입 DI (Dependency Injection) : 애플리케이션 실행 시점(런타임)에 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것 (동적인 객체 인스턴스 관계)
        - 의존관계 주입 사용시 이점
            1.  클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변
            경 가능
            2. 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경가
        - 의존관계 역전을 시켜주는 것 = “IoC 컨테이너” 또는 “DI 컨테이너”
    
    - 스프링으로 전환하기~
        
        @Configuration 이 붙으면 설정(구성)정보로 사용
        
        @Bean : 스프링 컨테이너에 등
        
        applicationContext.getBean(name : ”memberService”, MemberService.class); : name이랑 같은 이름의 메소드를 찾을거야! 타입은 MemberService!
        

- **섹션 4. 스프링 컨테이너와 스프링 빈**
    - 스프링 컨테이너 생성 과정
        
        ApplicationContext = 스프링 컨테이너 (인터페이스임!)
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%205.png)
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%206.png)
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%207.png)
        
    - 컨테이너 등록 빈 조회
        - *iter : 배열 for문 만들어줌
        - 스프링이 내부에서 사용하는 빈은 getRole() 로 구분 가능
        - ac.getBean(빈이름, 타입) : 스프링 컨테이너에서 빈 찾는 기본 방
    - 스프링 빈 조회 - 동일 타입이 둘 이상
        
        *static class : 클래스 안에 클래스 썼을 때 이 안에서만 쓸거다! 
        
    - 스프링 빈 조회 - 상속관계
        
        부모 타입으로 조회하면, 자식 타입도 함께 조회한다!
        

- **섹션 5. 싱글톤 컨테이너**
    - 웹 애플리케이션과 싱글톤
        
        여러명의 클라이언트가 동시에 서비스 요청 시 문제점 : 여러개의 객체가 생성 → 요청이 올 때마다 객체 생성 → 메모리 낭비 심함
        
        ****∴**** 해당 객체가 딱 1개 생성되고, 공유하도록 설계 → 싱클톤 패턴
        
    - 싱글톤 패턴
        - 정의 : 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
        - private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하게!
        - 방법
            1. **static 영역에 객체 instance를 미리 하나 생성**해서 올려둔다.
                1. ex) private static final SingletonService *instance*  = new SingletonService();
            2. 이 객체 **인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회**할 수 있다. 이 메서드를 호
            출하면 항상 같은 인스턴스를 반환한다.
                1. ex) public static SingletonService getInstance() {
                    
                            return *instance*;
                    
                    }
                    
            3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 **private으로 막아서** 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
                1. ex) private SingletonService(){}
    - 싱글톤 컨테이너(스프링 컨테이너)
        
        스프링 컨테이너 : 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리
        
        ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%92%E1%85%A2%E1%86%A8%E1%84%89%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%85%E1%85%B5%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%91%E1%85%A7%E1%86%AB%20e5abdff70dab4586b03daf02a34b7050/Untitled%208.png)
        
    - 싱글톤 방식의 주의점
        
        싱글톤 객체는 상태를 유지하게 설계하면 안된다!
        
        **※**사용자A 1만원 주문하고 사용자B 2만원 주문하면 사용자A 주문가격이 2만원으로?!!! 되는 문제 
        
        ****∴**** price 필드는 공유되는 필드인데 특정 클라이언트가 값을 변경한다 → 스프링은 항상 무상태로 설계
        
    - @Configuration과 싱글톤
    - @Configuration과 바이트코드 조작의 마법
    

**※ 클라이언트 코드에서 “인터페이스 = new 구현 클래스”를 통해 하면 생기는 문제점? :** 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경 → DIP 위반(인터페이스에만 의존해야되는데 구현 클래스에도 의존하기 때문) & OCP 위반

- DIP란?
    - DIP (Dependency inversion principle / 의존관계 역전 원칙) : 인터페이스에 의존, 구현 클래스 의존 X

**※ 위 문제를 해결하기 위한 해결방안? :** 인터페이스에만 의존할 수 있게 위부에서 생성자를 통해 구현 객체를 주입 ex) AppConfig