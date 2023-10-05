package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); // 메모리 회원 저장소 (오버라이딩)
    //private final DiscountPolicy discountPolicy= new FixDiscountPolicy(); // 정액 할인 정책 (오버라이딩)
    // 여기서 문제가 있다. 클라이언트 OrderServiceImpl은 인터페이스인 DiscountPolicy와 구현 FixDiscountPolicy클래스 둘 다에 의존을 한다. -> DIP 위반

    //private final DiscountPolicy discountPolicy= new RateDiscountPolicy();
    // 그래서 RateDiscountPolicy로 변경을 할 때에도 OrderServiceImpl의 코드를 변경해야 한다. -> OCP 위반


    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    // 최종, 인터페이스에만 의존하게 함. 무조건 값을 할당해야 하는 final을 지워버리자
    // 그러나 이 상태로 Test를 돌리면 아무것도 할당이 안되어 있기 때문에 Null pointer exception이 뜬다
    // 누군가 대신 구현 객체를 생성하고 넣어주어야 한다!

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberID, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberID); // 회원 정보 조회 후
        int discountPrice = this.discountPolicy.discount(member, itemPrice); //dicount는 너가 알아서 해라 discountPolicy. 이렇게 던져준 것임. 할인 관련 변경 들어와도 할인만 수정하면 되지 오더 서비스 수정 필요 없음. DIP 잘 지킴
        // 할인 정책에 그냥 조회한 회원과 상품가격을 넘김 (이때 넘기는 범위를 grade, member 전체 등 프로젝트 상황에 따라 다르게 한다)

        return new Order(memberID, itemName, itemPrice, discountPrice);   // 최종 주문 만들어서 반환만 해주면 오더 서비스 역할 끝!
    }

}
