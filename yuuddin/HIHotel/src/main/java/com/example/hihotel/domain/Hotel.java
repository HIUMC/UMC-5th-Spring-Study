package com.example.hihotel.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Hotel {

    @Id @GeneratedValue
    @Column(name = "hid", insertable = false, updatable = false)
    private Long id;

    private String name;

    private String address;

    private String phone_number;

    @OneToMany(mappedBy = "hotel")
    private List<Hotelier> hotelierList = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<HotelRoom> hotelRoomList = new ArrayList<>();
}
