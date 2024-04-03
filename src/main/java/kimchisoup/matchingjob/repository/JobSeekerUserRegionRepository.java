package kimchisoup.matchingjob.repository;


import kimchisoup.matchingjob.entity.dao.JobSeekerUserRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerUserRegionRepository extends JpaRepository<JobSeekerUserRegion,Long> {
}
