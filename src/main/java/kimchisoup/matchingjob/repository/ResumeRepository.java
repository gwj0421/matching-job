package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.WriteResumeForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>{
    Resume findByEmail(String email);
}
