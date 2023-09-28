package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // id 로 회원 찾기
    Optional<Member> findByName(String name); // name 으로 회원 찾기
    List<Member> findAll(); // 지금까지 저장한 모든 회원 리스트 반환
}