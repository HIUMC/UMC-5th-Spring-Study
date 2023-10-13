package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // TheadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // TheadB: B사용자 10000원 주문
        statefulService2.order("userB", 20000);

        // TheadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        // 하지만 기대한 10000원이 아닌 20000원이 출력된다,

        System.out.println("userA price : " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
