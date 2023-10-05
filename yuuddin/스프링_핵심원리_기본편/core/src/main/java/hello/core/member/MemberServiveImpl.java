package hello.core.member;

public class MemberServiveImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiveImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
