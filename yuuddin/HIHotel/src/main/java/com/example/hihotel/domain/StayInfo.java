package com.example.hihotel.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class StayInfo {

    @Id @GeneratedValue
    private Long stayInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hid", referencedColumnName = "hid", insertable = false, updatable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomnum", referencedColumnName = "roomnum", insertable = false, updatable = false)
    private HotelRoom hotelRoom;

    private Date checkInDate;

    private Date checkOutDate;
}
