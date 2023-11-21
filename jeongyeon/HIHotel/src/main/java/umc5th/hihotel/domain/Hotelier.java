package umc5th.hihotel.domain;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Hotelier {

    @Id @GeneratedValue
    @Column(name = "hotelier_id")
    private Long id;

    private String hlid;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
