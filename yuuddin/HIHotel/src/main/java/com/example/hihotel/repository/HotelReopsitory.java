package com.example.hihotel.repository;

import com.example.hihotel.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReopsitory extends JpaRepository<Hotel, Long> {
}
