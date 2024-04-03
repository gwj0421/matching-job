package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.common.InterestFieldType;
import kimchisoup.matchingjob.entity.dao.InterestField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestFieldRepository extends JpaRepository<InterestField, Long> {
    Optional<List<InterestField>> findInterestFieldsByInterestFieldType(InterestFieldType interestFieldType);
}
