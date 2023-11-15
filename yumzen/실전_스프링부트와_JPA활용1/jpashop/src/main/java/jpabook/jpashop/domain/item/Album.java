package jpabook.jpashop.domain.item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.jpashop.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A") //DB가 구분할 수 있게
@Getter
@Setter
public class Album extends Item {
    private String artist;
    private String etc;
}
