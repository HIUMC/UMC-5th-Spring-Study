package dbpractice.dbhomework.repository;

import dbpractice.dbhomework.domain.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
}
