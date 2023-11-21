# 실전! 스프링 부트와 JPA

- **프로젝트 환경설정**
    - Model : data를 실어서 view에 넘김
    - 정적 컨텐츠 : index.html 처럼 static 폴더에 있는거 바로 띄움
    - 템플릿 엔진, 렌더링 필요한거는 templates 폴더에
    - ./h2.bat 으로 디비 실행
    - **이거 공부해야될듯?!!**
        - @Entity : @Entity가 붙은 클래스는 JPA가 관리
        - @Id @GeneratedValue
        - entity manager ??
        - Transaction???
    - 컴포넌트 스캔 → 자동으로 스프링 빈에 등록
- **도메인 분석 설계**
    - 엔티티 클래스 개발
        - @Entity : 테이블과 링크될 클래스
        - @Id : 해당 테이블의 PK
        - @GeneratedValue : PK 생성규칙 표시 (값 생성 보장)
        - @Column(name = "member_id") : 테이블의 칼럼임을 표시
        - @Embeddable : 값 타입을 정의하는 곳에 표시
        - @Embedded : 값 타입을 사하는 곳에 표시
        - @JoinColumn(name = member_id") : 외래키 이름이 member_id
        
        - 연관관계주인 : fk가 가까운 곳 (주인은 아무것도 안해도 됨)
        - @OneToMany(mappedBy = "member") : Order 테이블의 ㅣmember에서 매핑된 애(읽기전용)
        
        - 상속관계매핑 → 상속관계 전략 지정을 부분클래스에
        - @Inheritance(strategy = InheritanceType.*SINGLE_TABLE*)
        
        - @Enumerated(EnumType.*STRING*)
        
        - @JoinTable(name = "category_item",        joinColumns = @JoinColumn(name = "category_id"),        inverseJoinColumns = @JoinColumn(name = "item_id"))
        
        - enum : 사용자가 자신이 필요한 정보를 열거(=선언, 정의)하여 사용하는 집합 자료형
    - 엔티티 설계시 주의점
        - 엔티티에 Setter 사용 가급적 자제 : 변경포인트가 많아 유지보수가 어려움
        - **모든 연관관계는 자연로딩으로!!!** :  EAGER는 N+1문제, join으로 인한 성능문제, 어떤 SQL이 실행될지 추적어려움 → LAZY로 설정!!!
            - @XToOne(OneToOne, ManyToOne)  은 기본이 EAGER → ex) **@ManyToOne(fetch = FetchType.LAZY) 해야됨!!**
            - @OneToMany는 기본이 LAZY
        - 컬렉션은 필드에서 바로 초기화하자!!
        - 연관관계 편의 메서드
            - 양방향의 경우 하나의 메소드에서 양측의 관계를 설정하게 해주는게 안
        
- **애플리케이션 구현 준비**
    - 애플리케이선 아키텍처
        - controller : 웹 계층
        - service : 비즈니스 로직, 트랜젝션 처리
        - repository : JPA 직접 사용 / 엔티티 매니저 사용
        - domain : 엔티티가 모여있는 계층 / 모든 계층에서 사
- 회원 도메인 개발
    - 회원 리포지토리
        - @PersistenceContext : EntityManager를 빈으로 주입
        - EntityManager : 영속성 관리
            - 쓰레드 간 EntityManager 공유 X
            - 스프링은 싱글톤 기반이라 모든 쓰레드가 빈을 공유하지만 스프링 컨테이너가 초기화되면서 @PersistenceContext로 주입받은 EntityManager를 Proxy로 감쌈 → Thread-Safe 보장!
        - em.persist(member) : JPA가 member 저장(영속성)
            - 영속성 : 데이터를 생성한 프로그램이 종료되어도 사라지지 않는 데이터의 특
        - em.createQuery("select m from Member m", Member.class) : “select문” = JPQL(Entity 대상으로 쿼리 날림), Member m이 반환 타입
        - setParameter :
        - @RequiredArgsConstructor : final 이 붙은거 생성자 만들어줌!
        
    - Test할때 Memory DB 사용~
- 주문 검색기능 개발
    - JPA에서 동적쿼리를 어떻게 해결?
        - Querydsl로 처리
        
- 웹 계층 개발
    1. 홈 화면과 레이아웃
        1. <div th:replace="fragments/bodyHeader :: bodyHeader" />
        2. resources:templates/ +{ViewName}+ .html
    2. 회원등록
        1. model.addAttribute("memberForm", new MemberForm()); ⇒ controller에서 view 넘어갈 때 데이터를 실어서 넘김
        2. Get은 form 화면을 열어보고, Post는 데이터를 실제 등록
        3. 오류 있으면 다시 등록하게
            
            public String create(@Valid MemberForm form, BindingResult result) {    if (result.hasErrors()) {        return "members/createMemberForm";    }
            
    - **API 만들때는 절대 Entity를 웹으로 반환 X !!!!**
        - api는 스펙, entity에 로직 추가했는데 api 스펙이 변하면 안됨
        - DTO : Data Transfer Object, 계층 간 데이터 전송을 위해 도메인 모델 대신 사용되는 객체
    
    - 상품 수정
        - @GetMapping("items/{itemId}/edit")public String updateItemForm(@PathVariable("itemId") Long )
    
    - ***** 머지가 머지?! *****
        - 변경 감지와 병합
            - 준영속 엔티티 : 영속성 컨텍스트가 더는 관리하지 않는 엔티티 → JPA를 한번 걸친거(식별ID가 있음)
            - 영속 엔티티는 변경 감지가 일어남! But 준영속은 JPA가 관리 X (BookForm form 에서 form.get어쩌구해서 새로운 book 객체 만들고 값을 바꿔도 디비에 없데이트 X)
        - 준영속 엔티티 수정방법 2가지
            1. 변경 감지 기능 사용 (dirty checking)
                1. @Transactionalpublic void updateItem(Long itemid, Book param) {
                    
                        Item findItem = itemRepository.findOne(itemid);
                    
            2. em.merge(item) : 값이 없으면 null로 들어가는 문
        - **엔티티 변경할 때는 항상 변경감지 사용**
        
    - @RequestParam("memberId") Long memberId : memberId에 있는거를  memberId 변수에 담아서 전달
    - @ModelAttribute  : 사용자가 요청시 전달하는 값을 오브젝트(객체) 형태로 매핑
    - @PathVariable("orderId") Long orderId :  url에서 각 구분자에 들어오는 값을 처리해야 할 때 사용

- **질문 (10/31)**
    
    <aside>
    💡 **Q. ㅁㅁㅁㅁ을 채우세용**
    
    ---
    
    @ㅁㅁㅁㅁ
    
    public class HelloController {
    
            @GetMapping("hello")
    
       public String hello(Model model) {
    
            model.addAttribute("data", "hello!!");
    
            return "hello";
    
        }
    
    }
    
    - **정답!**
        
        @Controller 
        
        - **그럼 Controller의 역할은 ?!!**
            
            모델과 뷰 사이 다리역할
            
            - 그럼 스프링에서 @Controller을 쓰면 어떤 일이 ??!!
                
                스프링 시작시 객체를 생성하여 스프링 컨테이너에서 컨트롤러 관리
                
                - @Controller에 어떤 어노테이션이 있어서 가능할까??!
                    
                    @Component 가 있어서 가능하다~
                    
                    - return “hello” 는 어떤 의미? (기능?, 효과?) 일까?!
                        
                        templates의 hello.html로 간다~
                        
    
    ---
    
    **Q. 다대다 는 어떻게 바꿔서 풀어내면 좋을까??**
    
    - **정답!**
        
        엔티티를 추가해서 다대다 관계를 일대다, 다대일 관계로~
        
    </aside>
    
- **질문 (11/7)**
    
    <aside>
    💡 **Q1. a와 b에 어떤게 들어갈까?**
    
    @Service
    
    **@ a. 여기를 채워주세용**
    
    - 정답!
        - **Transactional(readOnly = true) 붙인 이유는?! (hint: 왜 회원가입은 안붙었을까)**
            
            회원가입할 때는 readOnly가 아니거든!
            
    
    **@ b. 여기를 채워주세용**  
    
    - 정답
        - **RequiredArgsConstructor 은 무슨역할일까?! (hint : final)**
            
            final 붙은거 생성자 바로 만들어줘용
            
        
    
    public class MemberService {
    
        private **final** MemberRepository memberRepository;}
    
        */**     *회원 가입     */*   
    
       **@Transactional**
    
       public Long join(Member member) {
    
            validateDuplicateMember(member);    //중복회원 검증
    
           memberRepository.save(member);
    
            return member.getId(); 
    
       }
    
        private void validateDuplicateMember(Member member) {
    
            List<Member> findMembers =
    
             memberRepository.findByName((member.getName()));
    
            if (!findMembers.isEmpty()) {
    
                throw new IllegalStateException("이미 존재하는 회원입니다.");
    
            }
    
        }    //회원 전체 조회
    
        **@ a. 여기를 채워주세용 (위에랑 같은거)**
    
        public List<Member> fineMembers(){
    
            return memberRepository.findAll();
    
        }
    
        **@a. 여기를 채워주세용 (위에랑 같은거)**
    
        public Member findOne(Long memberId){
    
            return memberRepository.findOne(memberId);
    
        }
    
    }
    
    ---
    
    - **Q2. 모든 연관관계는 무슨 로딩으로 해야될까?**
    - **정답!**
        - 자연로딩 / 왜?
            - EAGER는 N+1문제,  join으로 인한 성능문제, 어떤 SQL이 실행될지 추적어려움 → LAZY로 설정!!! 그럼 어떤 연관관계에서 따로 지정해줘야될까?
                - 정답
                    
                    **@XToOne(OneToOne, ManyToOne)**  은 기본이 EAGER → ex) @ManyToOne(fetch = FetchType.LAZY) 해야됨!!
                    
    
    </aside>
    
- **질문(11/14)**
    - **1. @RequestParam, @ModelAttribute, @PathVariable의 차이점이 뭘까??!**
        - @RequestParam : 사용자가 요청시 전달하는 값을 매개변수로 1:1 맵핑
        - @ModelAttribute : 사용자가 요청시 전달하는 값을 오브젝트 형태로 매핑
        - @PathVariable :  url에서 각 구분자에 들어오는 값을 처리해야 할 때 사용
        
        ```jsx
        @PostMapping("/order")
            public String order(**@RequestParam("memberId") Long memberId,
                                @RequestParam("itemId") Long itemId,
                                @RequestParam("count")** **int count**) {
                orderService.order(memberId, itemId, count);
        
                return "redirect:/orders";
            }
        
        @PostMapping("/orders/{orderId}/cancel")
            public String cancelOrder(**@PathVariable("orderId") Long orderId**){
                orderService.cancelOrder(orderId);
                return "redirect:/orders";
            }
        
        @PostMapping("items/{itemId}/edit")
            public String updateItem(**@PathVariable Long itemId,
        														 @ModelAttribute("form") BookForm for**m) {
        				//Book book = new Book();
        //        book.setId(form.getId());
        //        book.setName(form.getName());
        //        book.setPrice(form.getPrice());
        //        book.setStockQuantity(form.getStockQuantity());
        //        book.setAuthor(form.getAuthor());
        //        book.setIsbn(form.getIsbn());
        //        itemService.saveItem(book);
        
                itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
                return "redirect:/items";
            }
        ```
        
        - **Q. updateItem에서 Book book = new Book(); 같은 방식으로 하지 않는 이유는??**
            
            A. saveItem으로 하면 merge 로 해서 변경하는거임!  머지가 머지?
            
            - 그래서 변경 감지 기능 사용 (dirty checking) 해야된다
                
                ```jsx
                @Transactional
                public void updateItem(Long itemid, Book param) {
                    Item findItem = itemRepository.findOne(itemid);
                		. . .
                ```