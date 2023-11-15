package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address(){ //함부로 new 생성하지 않게 (외부에서 직접 생성자를 호출할 수 없음 )
    }

    // city, street, zipcode와 같은 주소의 속성을 인자로 받아 새로운 주소 객체를 생성하는 생성자
    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
