package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.security.entity.Authority;
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
@DiscriminatorValue("JobSeekerUser")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUser extends SiteUser {
    private String githubToken;
    @Enumerated(EnumType.STRING)
    private RegionType residence;

    @OneToMany(mappedBy = "jobSeekerUser", cascade = CascadeType.REMOVE)
    private List<JobSeekerUserRegion> jobSeekerUserRegions = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser", cascade = CascadeType.REMOVE)
    private List<JobSeekerUserJobField> jobSeekerUserJobFields = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser", cascade = CascadeType.REMOVE)
    private List<JobSeekerUserProposal> jobSeekerUserProposals = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser", cascade = CascadeType.REMOVE)
    private List<Resume> resumes = new ArrayList<>();

    @Builder
    public JobSeekerUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl, Authority authority, String githubToken, RegionType residence) {
        super(name, email, password, phoneNumber, nickName, profileImageUrl, Authority.JOB_SEEKER);
        this.githubToken = githubToken;
        this.residence = residence;
    }

    public void addRegion(JobSeekerUserRegion region) {
        this.jobSeekerUserRegions.add(region);
    }

    public void addJobField(JobSeekerUserJobField jobField) {
        this.jobSeekerUserJobFields.add(jobField);
    }

    public void addProposal(JobSeekerUserProposal proposal) {
        this.jobSeekerUserProposals.add(proposal);
    }

    public void addResume(Resume resume) {
        this.resumes.add(resume);
    }
}
