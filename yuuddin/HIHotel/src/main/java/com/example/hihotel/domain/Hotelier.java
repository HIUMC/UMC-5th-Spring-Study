package com.example.hihotel.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Hotelier {

    @Id @GeneratedValue
    @Column(name = "hlid", insertable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hid", insertable = false, updatable = false)
    private Hotel hotel;

    private String name;
}
