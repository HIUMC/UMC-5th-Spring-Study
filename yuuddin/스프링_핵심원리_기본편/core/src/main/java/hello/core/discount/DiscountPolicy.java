package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @return이 할인 대상 금액임
     */
    int discount(Member member, int price);
}
