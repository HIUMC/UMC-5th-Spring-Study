package com.example.hihotel.repository;

import com.example.hihotel.domain.Hotelier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelierReopsitory extends JpaRepository<Hotelier, Long> {

}
