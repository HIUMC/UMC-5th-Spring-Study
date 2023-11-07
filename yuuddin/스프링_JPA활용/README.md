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

## 영속화

- **영속화란** 객체의 상태를 DB와 같은 데이터 저장소에 저장하는 것을 말한다.
    
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
    
- **test에서 memory DB 사용법**
    
    application.yml을 main과 따로 설정하는게 좋다. 
    
    → test>resources>application.yml을 만들고 url을 *`jdbc:h2:mem:test`*로 수정
    
    or → 아무것도 안써도 ok, b/c 스프링은 본래 메모리 작동 
    

## Test 관련

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