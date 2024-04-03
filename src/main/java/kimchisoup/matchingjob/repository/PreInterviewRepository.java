package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.PreInterviewList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreInterviewRepository {
    void saveOrUpdatePreInterviewList(PreInterviewList preInterviewList);

    PreInterviewList getPreInterviewListById(Long id);

    List<PreInterviewList> getAllPreInterviewLists();

    void deletePreInterviewList(Long id);
}
