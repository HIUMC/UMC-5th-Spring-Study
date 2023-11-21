package dbpractice.dbhomework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue
    @Column(name = "hotel_id")
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    // @OneToMany MappedBy 필요 - List<>
}
