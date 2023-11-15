package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //데이터 변경 시에는 꼭 필요
//@AllArgsConstructor //모든 생성자 만들어줌
@RequiredArgsConstructor //final에 대해서만 생성자 만들어줌
public class MemberService {


    private final MemberRepository memberRepository;

    /*
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    `@RequiredArgsConstructor` 써서 필요X
     */

    //회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //영속성 컨텍스트의 key=ID
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //@Transactional(readOnly = true)
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    //@Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
