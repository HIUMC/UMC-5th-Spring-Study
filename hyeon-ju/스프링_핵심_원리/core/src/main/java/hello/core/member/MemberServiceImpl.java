package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //구현 객체 선택해준다. 인터페이스만 있으면 Null pointer exception 터짐
   // 이 부분에서 MemberServiceImp은 인터페이스와 구현체 모두에 의존 -> DIP 위반

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 생성자를 통해 MemberRepository에 무슨 구현체가 들어갈건지 선택을 한다

    @Override
    public void join(Member member) {
        memberRepository.save(member); //join에서 save를 호출하면 다형성에 의해 MemberRepository인터페이스가 아니라 MemoryMemberRepository에 있는 save가 호출 됨(오버라이드)
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); //
    }
}
