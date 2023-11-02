package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {
    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberID = 1L; // primitive 타입인 long을 쓰면 null을 넣을 수가 없음. 나중에 DB에 null 넣을 때 불가능
        Member member = new Member(memberID, "memberA", Grade.Vip);
        memberService.join(member);

        Order order = orderService.createOrder(memberID, "item", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);//검증을 assertions (org-assertj.core.api)를 써야 method-chain..에서 편하게 테스트 만들 수 있음
    }
}
