package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_SEEKER_USER_COMPANY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUserCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_user_id")
    private JobSeekerUser jobSeekerUser;
    @ManyToOne
    @JoinColumn(name = "job_field_id")
    private Company company;

    @Builder
    public JobSeekerUserCompany(JobSeekerUser jobSeekerUser, Company company) {
        this.jobSeekerUser = jobSeekerUser;
        this.company = company;
    }
}

