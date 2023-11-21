package umc5th.hihotel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import umc5th.hihotel.domain.Customer;
import umc5th.hihotel.domain.StayInformation;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
