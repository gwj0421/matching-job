package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.HeadHunterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadHunterUserRepository extends JpaRepository<HeadHunterUser, Long> {

}