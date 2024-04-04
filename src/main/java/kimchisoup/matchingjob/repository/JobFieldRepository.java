package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.JobField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobFieldRepository extends JpaRepository<JobField, Long> {
    Optional<JobField> findJobFieldByJobCategoryAndSubJobCategory(JobCategory jobCategory, Enum subJobCategory);

    Optional<List<JobField>> findJobFieldsByJobCategory(JobCategory jobCategory);
}
