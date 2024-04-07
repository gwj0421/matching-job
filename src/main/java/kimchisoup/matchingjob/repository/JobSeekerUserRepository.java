package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerUserRepository extends JpaRepository<JobSeekerUser, Long> {
    JobSeekerUser findByEmail(String email);
}
