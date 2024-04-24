package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.ResumeJobField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeJobFieldRepository extends JpaRepository<ResumeJobField, Long> {
}
