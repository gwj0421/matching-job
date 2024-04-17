package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_SEEKER_USER_PROPOSAL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUserProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_user_id")
    private JobSeekerUser jobSeekerUser;
    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Builder
    public JobSeekerUserProposal(JobSeekerUser jobSeekerUser, Proposal proposal) {
        this.jobSeekerUser = jobSeekerUser;
        this.proposal = proposal;
    }
}
