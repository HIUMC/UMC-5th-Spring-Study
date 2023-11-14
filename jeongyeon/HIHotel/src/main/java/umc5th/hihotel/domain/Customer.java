package umc5th.hihotel.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private String id;


    private String name;

    private String num;

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
