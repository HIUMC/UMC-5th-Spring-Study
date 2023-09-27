package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("ahakim");

        //when
//        Long saveId = memberService.join(member);
//
//        //then
//        Member findMember = memberService.findOne(saveId).get();
//        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");


        //When
        // memberService.join(member1);
        // try {
        //     memberService.join(member2);
        //     fail();
        // }catch(IllegalStateException e){
        //     assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // }

//        memberService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class,
//                () -> memberService.join(member2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("")
    void findMembers() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("")
    void findOne() {
        // given

        // when

        // then
    }
}