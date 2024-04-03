package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

}
