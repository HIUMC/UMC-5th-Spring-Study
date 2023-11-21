# SPRING BOOT & JPA 활용

## 어노테이션들!

- @Entity : JPA를 사용해서 테이블과 매핑할 클래스에 붙여주는 어노테이션
    - 엔티티 안에서 사용할 수 있는 추가 기능
        
        `@Id` : 기본키 매핑
        
        `@GeneratedValue` : 기본키에 값을 자동 생성
        
        `@Column(name = "DB의 열이름")` : 엔티티의 필드를 DB의 열에 매핑
        
        ---
        
        `@Embedded` : 다른 클래스(`@Embeddable`을 사용해야 함)를 임베드 (캡슐화 느낌..?)
        
        `@Enumerated(EnumType.*STRING*)` : enum을 DB에 저장할 수 있게 함 
        
        → `EnumType.*STRING`과* `EnumType.ORDINAL` 이 존재
        
        → string은 문자열로 저장되어 여러값 도출 가능
        
        → ordinal은 0,1로 들어가 두가지 결과만 도출 가능
        
        ---
        
        `@OneToMany(mappedBy = "객체명")` : 1인 쪽에 붙으며 owner가 아님을 보여줌
        
        `@ManyToOne(fetch = FetchType.LAZY)` : Owner (Many인 쪽 / 외래키를 갖고 있는 부분) 쪽에 붙음
        
        ```java
        //Member class 내
        @OneToMany(mappedBy = "member")
        private List<Order> orders = new ArrayList<>();
        
        //Order class 내
        @ManyToOne
        @JoinColumn(name = "member_id")
        private Member member;
        ```
        
        `@JoinColumn(name = "저장할 컬럼명")` : name 속성은 말 그대로 Order 엔티티에 존재하는 member 라는 필드를 어떤 이름으로 Order 테이블에 컬럼명으로 설정할 것인지를 나타내주는 것 → Order 테이블에 member_id 라는 컬럼으로 member 필드가 들어감
        
        <aside>
        💡 모든 연관관계는 지연로딩(LAZY)으로 설정!
        
        </aside>
        
        → 즉시로딩 (EAGER)은 오류가 자주 발생한다.
        
        XToMany는 default가 LAZY인데 XToOne은 default가 EAGER이므로 항상 LAZY로 바꾸자
        
        ---
        
- `@Table(name= "테이블명")` : 엔티티와 DB의 table을 매핑
- `@Inheritance(strategy = InheritanceType.*SINGLE_TABLE*)` : 상속관계의 클래스들 구조 지정
    
    → `InheritanceType.*SINGLE_TABLE`:* 부모 클래스에 모든 자식 클래스의 속성들을 몰빵
    
    → `InheritanceType.JOINED`: 자식 클래스에 부모 클래스의 기본키 배정
    
- `@DiscriminatorColumn(name = "dtype")` : 부모 클래스에서 사용하고 dtype이라는 컬럼을 생성해 상속받는 클래스들의 키를 저장
- `@DiscriminatorValue("A")` : 자식 클래스에서 사용하고 dtype에 들어가는 값으로 자식 클래스들을 구별하는 데에 쓰임
- 

---

- OrderItem의 외부 직접 생성을 막아주는 2가지 방법 (1방법 추천)

```java
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {......}
```

```java
public class OrderItem {....
	protected OrderItem() {}.....}
```

이러면 구현한 생성 메서드만을 통해서 구현할 수 있다

## 엔티티의 생명 주기 (영속, 비영속, 준영속, 삭제)

- **비영속** 
영속성 컨텍스트와 관계 없는 새로운 상태
    
    ```java
    Member member = new Member();
    member.setId("member1");
    member.setUsername("회원1");
    ```
    
- **준영속**
    
    엔티티를 영속성 컨텍스트에서 분리 → 영속성 컨텍스트가 제공하는 기능 사요 불가
    
    ```java
    em.detach(member);           // member 엔티티만 준영속 상태로 전환
    em.clear();                  // 영속성 컨텍스트를 완전히 초기화
    em.close();                  // 영속성 컨텍스트를 종료
    ```
    
- **삭제**
- **영속화**
    
    객체의 상태를 DB와 같은 데이터 저장소에 저장하는 것을 말한다.
    
    ```java
    //repository 내
    @PersistenceContext
    private EntityManager em;
    ......
    em.persist(member);
    ```
    
    `@PersistenceContext` : entity 객체를 영속 상태로 유지하고 데이터베이스와의 일관성을 유지하기 위한 도구인 영속성 context를 주입하는 데에 사용 + entity manager를 주입받을 수 있음
    
    <aside>
    💡 근데 대신에 `@RequiredArgsConstructor` 를 쓰면 쓸 필요 X
    
    ```java
    @RequiredArgsConstructor
    public class MemberRepository {
        private final EntityManager em;}
    ```
    
    일관성있고 간편!!
    
    </aside>
    
    `EntityManager` : persistence context내에서 entity를 검색, 추가, 설정, 삭제할 수 있음
    
    `em.persist(member)`: entity manger를 통해 member(entity)를 영속화
    
    ![Untitled](SPRING%20BOOT%20&%20JPA%20%E1%84%92%E1%85%AA%E1%86%AF%E1%84%8B%E1%85%AD%E1%86%BC%20057c86b5a1d24c5694351995711a71bf/Untitled.png)
    
    - entity 검색
        
        ```java
        //repository 내
        public List<Member> findByName(String name) {
                return em.createQuery("select m from Member m where m.name = :name", Member.class)
                         .setParameter("name",name)
                         .getResultList(); }
        
        //service 내
        @Transactional(readOnly = true)
            public Member findOne(Long memberId) {
                return memberRepository.findOne(memberId); }
        ```
        
        `@Transactional` : DB 조작에 대한 안정성을 높임 (ex. 수정했을 때 오류가 나면 다시 롤백)
        
        `@Transactional(readOnly = true)` : 읽기만 가능(쓰기 불가) → 효율 ↑
        
    - 영속성 컨텍스트의 이점
        - 1차 캐시에서 엔티티 조회하고 반환해서 빠름
        - 동일성 보장 : 1차 캐시로 반복 가능한 읽기 등급의 격리 수준 제공
        - 변경 감지를 알아서 해줌
        - 지연 로딩 : commit을 해야 insert sql을 보냄
    - COMMIT과 FLUSH
        
        flush : 영속성 컨텍스트의 변경 내용을 DB에 반영
        
        transactional 작업 단위가 중요 → 커밋 직전에만 동기화하면 된다
        
        - em.flush : 직접 호출
        - transaction commit : flush 자동 호출

## Test 관련

- **test에서 memory DB 사용법**
    
    application.yml을 main과 따로 설정하는게 좋다. 
    
    → test>resources>application.yml을 만들고 url을 *`jdbc:h2:mem:test`*로 수정
    
    or → 아무것도 안써도 ok, b/c 스프링은 본래 메모리 작동 
    

---

```java
@RunWith(SpringRunner.class)
@SpringBootTest                   //이 두개가 있어야 spring boot를 끌어와서 테스트 가능
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2);    //예외 발생해야함!!
        //then
        fail("예외가 발생해야 한다.");
    }
```

## JPA에서 동적 쿼리를 처리하는 방법

- 변경 감지와 병합(merge)
    - 준영속 엔티티
        
        PersistenceContext가 관리하지 않는 엔티티
        
        ```java
        Book book = new Book();
        book.setId(form.getId());
        ```
        
        Book 개체는 이미 DB에 저장되어 식별자가 존재한다. 
        위 코드와 같이 임의로 만들어낸 엔티티(기존 식별자가 있) 가 준영속 엔티티다.
        
        ---
        
    - 준영속 엔티티를 수정하는 2가지 방법
        - 변경 감지 기능 사용
            
            ```java
            @Transactional
            void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
             Item findItem = em.find(Item.class, itemParam.getId()); //같은 엔티티를 조회한다.
             findItem.setPrice(itemParam.getPrice()); //데이터를 수정한다. }
            ```
            
            !!!→ `@Transactional`을 통해 commit 시점에 변경을 감지
            
        - 병합(merge) 기능 사용
            
            = 준영속 상태의 엔티티를 영속 상태로 변경하는 것
            
            ```java
            @Transactional
            void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
             Item mergeItem = em.merge(itemParam); }
            ```
            
            ![1. merge()를 실행
            2. 준영속 엔티티의 식별자 값으로 1차 캐시에서 엔티티를 조회
            3. 조회한 영속 엔티티에 member 엔티티 값을 채운다
            4. 영속 상태인 mergeMember가 반환](SPRING%20BOOT%20&%20JPA%20%E1%84%92%E1%85%AA%E1%86%AF%E1%84%8B%E1%85%AD%E1%86%BC%20057c86b5a1d24c5694351995711a71bf/Untitled%201.png)
            
            1. merge()를 실행
            2. 준영속 엔티티의 식별자 값으로 1차 캐시에서 엔티티를 조회
            3. 조회한 영속 엔티티에 member 엔티티 값을 채운다
            4. 영속 상태인 mergeMember가 반환
            
            > 병합은 모든 속성이 병합된다. ⇒ 데이터가 없으면 null로 바뀔 위험 있음
            > 
            
        
        <aside>
        💡 모범 사용 예시
        
        ```java
        public void save(Item item) {
                if (item.getId() == null) {
                    em.persist(item);        
                }else {
                    em.merge(item); }}
        ```
        
        그런데 이는 item의 식별키가 @GeneratedValue를 통해 자동생성되서 persist 수행이 가능한 것
        
        </aside>
        

## 기본키 복합키 외래키

- **단순 기본키**
    
    ```java
    @Id @GeneratedValue
        @Column(name = "HID")
        private Long id;
    ```
    
- **복합키**
    
    `Hotel (HID, RoomNum)` 을 구현한다고 가정
    
    ---
    
    1. `@Embeddable` 이용
        
        ```java
        @Embeddable
        class HotelRoomId implements Serializable {
            @Column(name = "HID")
            private Long id;
        
            @Column(name = "RoomNum")
            private String roomNum;
        }
        ```
        
        ```java
        @EmbeddedId
        private HotelRoomId hotelRoomId;
        ```
        
    2. `@IdClass` 이용
        
        ```java
        class HotelRoomId implements Serializable {
            private Long id;
        
            private String roomNum;
        }
        ```
        
        ```java
        @Entity
        @IdClass(HotelRoomId.class)
        class HotelRoom {
            @Id @Column(name = "RoomNum")
            private String roomNum;
        
            @Id @Column(name = "HID")
            private Long id;
        }
        ```
        
    
- **외래키를 기본키로**
    
    `HotelRoomId (HID(FK,PK), RoomNum)`을 구현한다고 가정
    
    ```java
    @Embeddable
    class HotelRoomId implements Serializable {
        private Long id;
        private String roomNum;
    }
    ```
    
    ```java
    @EmbeddedId
    private HotelRoomId hotelRoomId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HID")
    private Hotel hotel;
    ```
    
    이와 같이 먼저 복합키 설정을 해준 후 owner class에서 외래키 설정을 해준다
    

## 톰캣!

톰캣은 단순한 정적 Web-Server가 아니며, **Web-Server와 함께 Servlet-Container를 합쳐놓은 미들웨어라는 점**이다. 그리고 이 **톰캣의 Server-Container에 스프링의 DispatchServlet이 배포되어 실행된다.**

스프링 프레임워크의 중심에 있는 DispatcherServlet이 톰캣의 Servlet-Continer에 배포되어 돌아가는 Servlet중 하나일 뿐이라는 것

DispatcherServlet은 client에서 요청이 들어오면 요청을 처리할 적당한 controller와 메서드를 찾아 요청을 위임한다.

![DispatcherServlet의 동작 과정](SPRING%20BOOT%20&%20JPA%20%E1%84%92%E1%85%AA%E1%86%AF%E1%84%8B%E1%85%AD%E1%86%BC%20057c86b5a1d24c5694351995711a71bf/Untitled%202.png)

DispatcherServlet의 동작 과정

⇒ 스프링 부트가 자동으로 톰캣을 실행시키고 DispatcherServlet을 배포한다

> **톰캣 ⊃ Web-Server, {Servlet-Container ⊃ DispatchServlet** (= 스프링 프레임워크의 중심)}
> 

## JPA REPOSITORY

여지껏 만든 findAll findByString이런걸 모두 구현해준다

jpa repository는 org.springframework.data.jpa.repository 패키지의 "JpaRepository"라는 인터페이스를 상속한다. 

```java
@Repository
public class 리포지토리이름 exteds JpaRepository <엔티티 클래스이름 , ID 필드 타입> {}
```

---

- **jpa repository가 구현해주는 method**
    
    
    | save | 레코드 저장 (insert, update) |
    | --- | --- |
    | findOne | primary key로 레코드 한건 찾기 |
    | findAll | 전체 레코드 불러오기. 정렬(sort), 페이징(pageable) 가능 |
    | count | 레코드 갯수 |
    | delete | 레코드 삭제 |

api 만들 때는 절대 엔티티를 넘기면 안된다

1. 영속 컨텍스트에서 em.find(Member.class, ”member1”);을 했다고 가정하고 
    
    member1이 (———)에 있다면 바로 갖고 오지만
    
    (———)에 없다면 어떤 일이 일어나는지! (3 단계)
    
    - 답
        
        빈칸 : 1차 캐시
        
        디비를 조회해서 1차 캐시에 저장하고 엔티티를 반환한다
        
2. em.persist(memberA)를 수행했다고 가정하면 INSERT SQL은 어디에 있을까 ?
    1. 1차 캐시
    2. 데이터베이스
    3. 쓰기 지연 SQL 저장소
    - 답
        
        C
        
3. flush와 commit의 차이점은 무엇인가
    - 답
        
        flush는 쿼리를 전송하는 역할이고 commit은 내부적으로 flush를 수행한 뒤 트랜잭션을 끝내는 역할 ⇒ **flush로 전송된 쿼리는 rollback할 수 있지만 commit은 트랜잭션을 끝내므로 rollback 할 수 없습니다.**