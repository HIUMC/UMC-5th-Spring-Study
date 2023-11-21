package dbpractice.dbhomework.repository;

import dbpractice.dbhomework.domain.StayInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StayInformationRepository extends JpaRepository<StayInformation, Long> {

}
