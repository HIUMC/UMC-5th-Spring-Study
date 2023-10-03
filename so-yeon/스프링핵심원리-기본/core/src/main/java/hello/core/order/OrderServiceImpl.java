package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;


public class OrderServiceImpl implements OrderService{
    /**
     * OrderService 입장에서는 할인에 대해서 모름.
     * 할인에 대한 것은 discountPolicy가 알아서 하고
     * 결과만 OrderService에게 알려줘.
     * 단일책임원칙이 잘 지켜진 예시임.
    **/
    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    /**
     * DiscountPolicy : 추상 클래스에도 의존
     * FixDiscountPolicy : 구현 클래스에도 의존
     * 둘다 의존하고 있음 -> DIP 위반!
     * DIP : 구체에 의존하지 말고, 항상 추상에 의존하라.
     */
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
