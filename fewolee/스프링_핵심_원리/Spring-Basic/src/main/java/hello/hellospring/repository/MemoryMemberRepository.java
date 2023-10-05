package hello.hellospring.repository;

import java.util.*;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

public class MemoryMemberRepository implements MemberRepository {
    //실무에서 동시성 문제로 ConcurrentHashMap 써야할 수도 있으나 예제라서 HashMap 그냥 사용
    private static Map<Long, Member> store = new HashMap<>();
    //실무에서 동시성 문제로 AtomLong 등 사용해야 하나 여기서도 예제상 그냥 Long 사용
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //null체크를 Optional로 감싸서 처리
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    
    //findAny()는 가장 먼저 찾은 요소를 Optional로 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                    .filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
