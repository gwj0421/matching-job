package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_SEEKER_USER_INTEREST_FIELD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUserJobField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_user_id")
    private JobSeekerUser jobSeekerUser;
    @ManyToOne
    @JoinColumn(name = "job_field_id")
    private JobField jobField;

    public JobSeekerUserJobField(JobSeekerUser jobSeekerUser, JobField jobField) {
        this.jobSeekerUser = jobSeekerUser;
        this.jobField = jobField;
    }
}
