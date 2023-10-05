package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //build.gradle에서 spring-data-jpa 라이브러리 의존성 추가시 자동 생성 by springboot
    //JPA쓰려면 EnttityManage 주입받기
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //INSERT를 처리
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //SELECT를 처리
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //JPQL
        //스프링데이터JPA사용하면 JPQL도 안 써도 됨
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        //JPQL : 테이블이 대상이 아닌 객체(ENTITY)를 대상으로 쿼리를 날리는 기술
        //SELECT *이 아닌 m(객체 자체를 조회)
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
