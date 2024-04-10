package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.PreInterviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreInterviewAnswerRepository extends JpaRepository<PreInterviewAnswer, Long> {
    Optional<List<PreInterviewAnswer>> findPreInterviewAnswerByJobSeekerUserId(Long jobSeekerUserId);
}
