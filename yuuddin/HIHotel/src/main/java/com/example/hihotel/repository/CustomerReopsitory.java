package com.example.hihotel.repository;

import com.example.hihotel.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReopsitory extends JpaRepository<Customer, Long> {
}
