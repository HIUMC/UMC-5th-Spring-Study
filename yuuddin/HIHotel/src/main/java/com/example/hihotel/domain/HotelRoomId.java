package com.example.hihotel.domain;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
public class HotelRoomId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hid", referencedColumnName = "hid", insertable = false, updatable = false)
    private Hotel hotel;

    private Long roomnum;
}
