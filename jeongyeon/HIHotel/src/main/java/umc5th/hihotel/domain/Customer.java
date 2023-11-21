package umc5th.hihotel.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String cid;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<StayInformation> stayInformations = new ArrayList<>();

//    public void addBooking(Booking booking) {
//        bookings.add(booking);
//    }
//
//    public void addStayInformation(StayInformation stayInformation) {
//        stayInformations.add(stayInformation);
//    }

}
