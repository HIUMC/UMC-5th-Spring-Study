package umc5th.hihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc5th.hihotel.domain.StayInformation;

public interface StayInformationRepository extends JpaRepository<StayInformation, Long> {
}
