package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
