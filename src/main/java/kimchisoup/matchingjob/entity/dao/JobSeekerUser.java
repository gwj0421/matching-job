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

    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserRegion> jobSeekerUserRegions = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserJobField> jobSeekerUserJobFields = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserProposal> jobSeekerUserProposals = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<Resume> resumes = new ArrayList<>();
    @OneToMany(mappedBy = "jobSeekerUser")
    private List<JobSeekerUserCompany> jobSeekerUserCompanies = new ArrayList<>();

    @Builder
    public JobSeekerUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl, Authority authority, String githubToken, RegionType residence) {
        super(name, email, password, phoneNumber, nickName, profileImageUrl, Authority.JOB_SEEKER);
        this.githubToken = githubToken;
        this.residence = residence;
    }

    public void addJobSeekerUserRegion(JobSeekerUserRegion jobSeekerUserRegion) {
        this.jobSeekerUserRegions.add(jobSeekerUserRegion);
    }

    public void removeJobSeekerUserRegion(JobSeekerUserRegion jobSeekerUserRegion) {
        this.jobSeekerUserRegions.remove(jobSeekerUserRegion);
    }

    public void addJobSeekerUserJobFields(JobSeekerUserJobField jobSeekerUserJobField) {
        this.jobSeekerUserJobFields.add(jobSeekerUserJobField);
    }

    public void removeJobSeekerUserJobFields(JobSeekerUserJobField jobSeekerUserJobField) {
        this.jobSeekerUserJobFields.remove(jobSeekerUserJobField);
    }

    public void addJobSeekerUserProposal(JobSeekerUserProposal jobSeekerUserProposal) {
        this.jobSeekerUserProposals.add(jobSeekerUserProposal);
    }

    public void removeJobSeekerUserProposal(JobSeekerUserProposal jobSeekerUserProposal) {
        this.jobSeekerUserProposals.remove(jobSeekerUserProposal);
    }

    public void addResume(Resume resume) {
        this.resumes.add(resume);
    }

    public void addSubscribedCompany(JobSeekerUserCompany jobSeekerUserCompany) {
        this.jobSeekerUserCompanies.add(jobSeekerUserCompany);
    }

    public void removeSubscribedCompany(JobSeekerUserCompany jobSeekerUserCompany) {
        this.jobSeekerUserCompanies.remove(jobSeekerUserCompany);
    }
}
