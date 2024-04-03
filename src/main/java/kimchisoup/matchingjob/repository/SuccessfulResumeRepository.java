package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.SuccessfulResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuccessfulResumeRepository extends JpaRepository<SuccessfulResume, Long> {
    Optional<List<SuccessfulResume>> findSuccessfulResumesByCompanyId(String companyId);
}
