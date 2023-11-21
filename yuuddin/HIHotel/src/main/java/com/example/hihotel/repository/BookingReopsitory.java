package com.example.hihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hihotel.domain.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingReopsitory extends JpaRepository <Booking, Long> {
    //public Member findbyhid뭐 이렇게 따로 만들고
}
