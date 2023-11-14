package dbpractice.dbhomework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class StayInformation {

    @Id
    @GeneratedValue
    @Column(name = "stayinformation_id")
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotelroom_id")
    private HotelRoom hotelroom;

    private Date checkindate;
    private Date checkoutdate;
}
