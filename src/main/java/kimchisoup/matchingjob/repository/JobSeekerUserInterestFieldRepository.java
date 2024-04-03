package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.JobSeekerUserInterestField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerUserInterestFieldRepository extends JpaRepository<JobSeekerUserInterestField,Long> {
}
