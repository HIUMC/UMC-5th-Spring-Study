package umc5th.hihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc5th.hihotel.domain.Hotel;
import umc5th.hihotel.domain.StayInformation;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
