package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
//해당 클래스와 그 하위 클래스 간의 상속 매핑 전략을 정의(여러 하위 엔티티 클래스가 단일 데이터베이스 테이블에 매핑됨)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;

    private int stockQuantity; //생성자로 값을 바꾸지 말고 핵심 메서드를 만들어서 값을 바꾸자.

    @ManyToMany(mappedBy = "items") //Category의 items 필드에서 관계 관리
    private List<Category> categories= new ArrayList<>();

    // 비즈니스 로직
    public void addStock(int quantity){
        this.stockQuantity += quantity; //재고수량 quantity만큼 증가시키는 로직
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
