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
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String phone_number;
}
