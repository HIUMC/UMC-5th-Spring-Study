package umc5th.hihotel.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class StayInformation {

    @Id @GeneratedValue
    @Column(name = "stayinformation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotelroom_id")
    private HotelRoom hotelRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date checkin;

    private Date checkout;

/*    public static StayInformation createStayInformation(HotelRoom hotelroom, Date checkin, Date checkout) {
        StayInformation stayInformation = new StayInformation();
        stayInformation.setHotelRoom(hotelroom);
        stayInformation.setCheckin(checkin);
        stayInformation.setCheckout(checkout);

        return stayInformation;
    }*/


}
