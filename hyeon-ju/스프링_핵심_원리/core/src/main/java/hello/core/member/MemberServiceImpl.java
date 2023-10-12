package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired //의존관계 자동 주입 (의존관계 직접 명시되어 있지 않으므로)
    //ac.getBean(MemberRepository.class) 라는 동작을 한다고 보면 된다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 생성자를 통해 MemberRepository에 무슨 구현체가 들어갈건지 선택을 한다

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); //
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
