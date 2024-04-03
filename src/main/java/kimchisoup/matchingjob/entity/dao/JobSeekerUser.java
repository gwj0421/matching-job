package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.RegionType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOB_SEEKER_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUser extends SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String githubToken;
    @Enumerated(EnumType.STRING)
    private RegionType residence;

    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserRegion> jobSeekerUserRegions = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserInterestField> jobSeekerUserInterestFields = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserProposal> jobSeekerUserProposals = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<Resume> resumes = new ArrayList<>();

    @Builder
    public JobSeekerUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl, String githubToken, RegionType residence) {
        super(name, email, password, phoneNumber, nickName, profileImageUrl);
        this.githubToken = githubToken;
        this.residence = residence;
    }

    public void addRegion(JobSeekerUserRegion region) {
        this.jobSeekerUserRegions.add(region);
    }

    public void addInterestField(JobSeekerUserInterestField interestField) {
        this.jobSeekerUserInterestFields.add(interestField);
    }

    public void addProposal(JobSeekerUserProposal proposal) {
        this.jobSeekerUserProposals.add(proposal);
    }
}
