package umc5th.hihotel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import umc5th.hihotel.domain.Booking;
import umc5th.hihotel.domain.StayInformation;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
