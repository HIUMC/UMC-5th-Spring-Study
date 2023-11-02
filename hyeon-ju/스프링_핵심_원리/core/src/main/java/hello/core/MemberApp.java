package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); //memberService에는 MemberServiceImpl이 들어가 있음
        //appConfig에서 memberService를 직접 찾아오는 것이 아닌
        //MemberService memberService = new MemberServiceImpl(); //기존에는 내가 직접 메인 메소드에서 MemberServiceImpl을 생성 해줌

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//스프링의 시작, 스프링 컨테이너라고 보면 됨. 얘가 모든 것을 관리해 줌
        // AppConfig에 있는 환경 설정 정보를 가지고 스프링 컨테이너에 객체 생성한 것을 집어 넣어서 관리 해준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //이름은 첫번째 파라미타, 타입은 두번째 파라미타야.해서 찾아 꺼낸다.
        //스프링 컨테이너를 통해서 직접 찾아와야 한다.

        Member member = new Member(1L, "memberA", Grade.Vip); //Long타입이라 1뒤에 L붙여주어야 함
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
