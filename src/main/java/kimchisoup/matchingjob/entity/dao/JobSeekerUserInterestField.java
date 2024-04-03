package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_SEEKER_USER_INTEREST_FIELD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUserInterestField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_user_id")
    private JobSeekerUser jobSeekerUser;
    @ManyToOne
    @JoinColumn(name = "interest_field_id")
    private InterestField interestField;

    public JobSeekerUserInterestField(JobSeekerUser jobSeekerUser, InterestField interestField) {
        this.jobSeekerUser = jobSeekerUser;
        this.interestField = interestField;
    }
}
