package com.example.hihotel.domain;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BookingId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hid", referencedColumnName = "hid", insertable = false, updatable = false)
    @JoinColumn(name = "roomnum", referencedColumnName = "roomnum", insertable = false, updatable = false)
    private HotelRoom hotelRoom;
}
