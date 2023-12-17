package dbpractice.dbhomework.repository;

import dbpractice.dbhomework.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface BookingRepository extends JpaRepository<Booking, Long> {

}
