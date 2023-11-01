package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); //회원을 저장
    //Optional : null값 반환 고려
    Optional<Member> findById(Long id); //id로 저장된 회원 찾기
    Optional<Member> findByName(String name); //name으로 저장된 회원 찾기

    List<Member> findAll(); //저장된 모든 회원의 List를 출력

}