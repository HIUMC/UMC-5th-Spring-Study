package umc5th.hihotel.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class HotelRoom {

    @Id @GeneratedValue
    @Column(name = "hotelroom_id")
    private int room_num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @OneToMany(mappedBy = "hotelRoom")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "hotelRoom")
    private List<StayInformation> stayInformations = new ArrayList<>();

    private int price;
}
