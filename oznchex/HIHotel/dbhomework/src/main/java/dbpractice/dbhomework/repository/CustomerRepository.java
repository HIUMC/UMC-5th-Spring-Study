package dbpractice.dbhomework.repository;

import dbpractice.dbhomework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
