package com.example.hihotel.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class HotelRoom {

    @Id @GeneratedValue
    private Long hotelRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hid", insertable = false, updatable = false)
    private Hotel hotel;

    private String roomNum;

    private int price;

    @OneToMany(mappedBy = "hotelRoom")
    private List<Booking> bookingList = new ArrayList<>();

    @OneToMany(mappedBy = "hotelRoom")
    private List<StayInfo> stayInfoList = new ArrayList<>();
}
