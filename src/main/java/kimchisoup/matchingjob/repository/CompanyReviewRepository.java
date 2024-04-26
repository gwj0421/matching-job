package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {
    long count();
}

