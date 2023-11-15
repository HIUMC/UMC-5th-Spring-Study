package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy ="delivery",fetch = FetchType.LAZY) //Order의 delivery 필드에서 이 관계가 관리됨
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL은 오류 가능성 높다. 절대쓰지X
    private DeliveryStatus status; //READY, COMP
}
