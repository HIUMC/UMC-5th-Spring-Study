package dbpractice.dbhomework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class HotelRoom {

    @Id
    @GeneratedValue
    @Column(name = "hotelroom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String roomnumber;
    private int price;
}
