package com.example.hihotel.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Customer {

    @Id @GeneratedValue
    @Column(name = "cid", insertable = false, updatable = false)
    private Long id;

    private String name;

    private String phonenum;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookingListList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<StayInfo> stayInfoList = new ArrayList<>();
}