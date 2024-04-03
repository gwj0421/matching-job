package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.JobSeekerUserProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerUserProposalRepository extends JpaRepository<JobSeekerUserProposal,Long> {
}
