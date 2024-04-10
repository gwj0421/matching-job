package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.PreInterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreInterviewQuestionRepository extends JpaRepository<PreInterviewQuestion, Long> {
    Optional<List<PreInterviewQuestion>> findByHeadHunterUserId(Long headHunterUserId);


}
