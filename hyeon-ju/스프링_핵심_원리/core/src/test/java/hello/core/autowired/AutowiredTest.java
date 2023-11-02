package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);//TestBean이 스프링 빈으로 등록이 된다.
        //ac.getBean(); //TestBean 받기
    }

    static class TestBean {

        @Autowired(required = false) //false 안해주면 멤버가 빈으로 등록되는 것이 아니라서 오류가 난다
        //required = false를 해두면 의존관계가 없을 시, 메소드 자체가 호출이 아예 안된다.
        public void setNoBean1(Member noBean1) { //Member는 스프링 관련된 빈이 아님. 즉 스프링 컨테이너에서 관리 되는 것이 아닌 것을 아무거나 집어 넣은 것.
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { //호출은 되지만, null로 들어간다
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
