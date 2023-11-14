package umc5th.hihotel.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id @GeneratedValue
    @Column(name = "hotel_id")
    private String id;

    private String name;

    private String num;

    private String address;

    @OneToMany(mappedBy = "hotel")
    private List<Hotelier> hoteliers = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<HotelRoom> hotelRooms = new ArrayList<>();
}
