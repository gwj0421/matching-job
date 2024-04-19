package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>{
    List<Resume> findAllByJobSeekerUser_Id(long jobSeekerUserId);
}
