package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item", //category_item이라는 중간 테이블 생성
        joinColumns = @JoinColumn(name="category_id"), //category_id 열을 사용하여 현재 엔티티(카테고리 엔티티)와 중간 테이블 간의 관계를 매핑
            inverseJoinColumns = @JoinColumn(name="item_id") //item_id 열을 사용하여 다른 엔티티(아이템 엔티티)와 중간 테이블 간의 관계를 매핑
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY) //Category의 parent 필드가 관계 관리
    private List<Category> child = new ArrayList<>();

    //연관관계 메서드 (부모 카테고리와 자식 카테고리 간의 관계가 설정)
    public void addChildCategory(Category child){
        // 현재 카테고리(this)의 자식 카테고리 목록(child)에 새로운 자식 카테고리(child)를 추가
        this.child.add(child);
        // 새로운 자식 카테고리(child)의 부모를 현재 카테고리(this)로 설정
        child.setParent(this);
    }
}
