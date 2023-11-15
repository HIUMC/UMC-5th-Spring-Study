package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy="member") //엔티티 클래스 Order의 member 필드를 사용하여 이 관계가 관리된다
    private List<Order> orders = new ArrayList<>(); //하이버네이트가 관리하고 있기 때문에 바꾸지말자.

}
