package umc5th.hihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc5th.hihotel.domain.HotelRoom;
import umc5th.hihotel.domain.StayInformation;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
}
