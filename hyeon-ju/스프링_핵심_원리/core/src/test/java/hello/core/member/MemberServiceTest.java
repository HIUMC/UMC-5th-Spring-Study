package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService; //Test는 appconfig에서 바로 꺼내주기가 애매하다
    @BeforeEach
    public void beforeEach() { //BeforeEach란 테스트를 실행하기 전에 무조건 실행이 되게 함, Test 2개면 2번 돌듯이 Test 갯수대로 돈다.
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given: 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.Vip);

        //when: 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then: 이렇게 된다
        Assertions.assertThat(member).isEqualTo(findMember); //검증은 Assertions 사용
    }
}

//오류가 나면 바로 x가 뜨면서 나온다. 어떤 부분에서 테스트가 실패했는지 빨리 캐치가 가능. 테스트 코드는 필수 중에 필수!!!
