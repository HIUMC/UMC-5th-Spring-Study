package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store= new HashMap<>(); // 저장소이니 Map 필요, 실무에서는 동시성 이슈 때문에 Concurrent HashMap을 써 주는 것이 좋음.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
