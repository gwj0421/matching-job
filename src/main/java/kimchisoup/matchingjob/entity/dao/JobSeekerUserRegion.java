package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_SEEKER_USER_REGION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUserRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_user_id")
    private JobSeekerUser jobSeekerUser;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder
    public JobSeekerUserRegion(JobSeekerUser jobSeekerUser, Region region) {
        this.jobSeekerUser = jobSeekerUser;
        this.region = region;
    }
}
