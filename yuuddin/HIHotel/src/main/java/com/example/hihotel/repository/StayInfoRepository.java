package com.example.hihotel.repository;

import com.example.hihotel.domain.StayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayInfoRepository extends JpaRepository<StayInfo, Long> {
}
