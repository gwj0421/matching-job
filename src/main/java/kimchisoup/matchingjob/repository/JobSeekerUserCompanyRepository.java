package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.JobSeekerUserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerUserCompanyRepository extends JpaRepository<JobSeekerUserCompany, Long> {

}
