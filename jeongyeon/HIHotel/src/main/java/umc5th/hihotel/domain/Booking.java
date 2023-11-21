package umc5th.hihotel.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Booking {

    @Id @GeneratedValue
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotelroom_id")
    private HotelRoom hotelRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date checkin;

    private Date checkout;

    public static Booking createBooking(HotelRoom hotelroom, Date checkin, Date checkout) {
        Booking booking = new Booking();
        booking.setHotelRoom(hotelroom);
        booking.setCheckin(checkin);
        booking.setCheckout(checkout);
//        this.checkin = checkin;
//builder 검색해보기
        return booking;
    }
}
