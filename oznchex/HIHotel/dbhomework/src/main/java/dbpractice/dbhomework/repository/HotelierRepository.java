package dbpractice.dbhomework.repository;

import dbpractice.dbhomework.domain.Hotelier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelierRepository extends JpaRepository<Hotelier, Long> {
}
