package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.PreInterviewAnswer;
import kimchisoup.matchingjob.entity.dao.PreInterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreInterviewRepository extends JpaRepository<PreInterviewQuestion, Long> {
    void saveOrUpdatePreInterviewList(PreInterviewAnswer preInterviewAnswer);

    Optional<PreInterviewAnswer> getPreInterviewListById(Long id);

    List<PreInterviewAnswer> getAllPreInterviewLists();

    void deletePreInterviewList(Long id);
}
