package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)     //ORDINAL 사용시 0,1로 설정되서 상태가 추가되면 장애 발생 -> STRING 사용
    private DeliveryStatus status;   //배송상태 [READY, COMP]
}