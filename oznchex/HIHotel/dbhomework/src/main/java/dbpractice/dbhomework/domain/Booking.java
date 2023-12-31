package dbpractice.dbhomework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue
    @Column(name = "booking_id")
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

    private Date bookingcheckindate;
    private Date bookingcheckoutdate;

    public Booking(Long id) {
        this.id = id;
    }

    public Booking(Long id, Customer customer, Hotel hotel, HotelRoom hotelroom, Date bookingcheckindate, Date bookingcheckoutdate) {
        this.id = id;
        this.customer = customer;
        this.hotel = hotel;
        this.hotelroom = hotelroom;
        this.bookingcheckindate = bookingcheckindate;
        this.bookingcheckoutdate = bookingcheckoutdate;
    }
}
