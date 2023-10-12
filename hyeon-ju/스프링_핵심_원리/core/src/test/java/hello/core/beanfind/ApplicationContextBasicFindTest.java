package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 검증은 Assertions
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); //이름 제거
        //현재는 인터페이스로 조회, 인터페이스의 구현체가 대상이 됨
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 검증은 Assertions
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); //스프링 컨테이너에 저런 객체가 등록이 되어있으면 조회 가능
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 검증은 Assertions
    }
    //그러나 구체 타입으로 적는 것은 안좋다. 유연성이 떨어짐 -> 역할과 구현 구분해야 하고, 역할에 의존 해야함.

    @Test
    @DisplayName("빈 이름으로 조회X") //실패 경우의 test도 만들어야 한다
    void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class)); //자바파일의 람다 기능 사용, 위에 static import 두개
        //오른쪽 로직을 실행하면 왼쪽의 예외가 터지면 성공
    }
}
