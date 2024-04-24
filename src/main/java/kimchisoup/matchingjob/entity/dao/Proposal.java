package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.*;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROPOSAL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Proposal extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private JobCategory jobCategory;
    private int subJobCategoryIdx;
    @Enumerated(EnumType.STRING)
    private CareerExperience careerExperience;
    private String pay;
    @Enumerated(EnumType.STRING)
    private EducationField educationField;
    private String workType;
    @Enumerated(EnumType.STRING)
    private RegionType workRegion;
    private String selfInterviewQuestion;
    private String requirement;
    private String preference;
    private String welfare;
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;
    private int submitterCnt = 0;

    @ManyToOne
    @JoinColumn(name = "head_hunter_id")
    private HeadHunterUser headHunterUser;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "proposal")
    private List<JobSeekerUserProposal> jobSeekerUserProposals = new ArrayList<>();


    @Builder
    public Proposal(JobCategory jobCategory, int subJobCategoryIdx, CareerExperience careerExperience, String pay, EducationField educationField, String workType, RegionType workRegion, String selfInterviewQuestion, String requirement, String preference, String welfare, OfferStatus offerStatus, int submitterCnt, HeadHunterUser headHunterUser, Company company) {
        this.jobCategory = jobCategory;
        this.subJobCategoryIdx = subJobCategoryIdx;
        this.careerExperience = careerExperience;
        this.pay = pay;
        this.educationField = educationField;
        this.workType = workType;
        this.workRegion = workRegion;
        this.selfInterviewQuestion = selfInterviewQuestion;
        this.requirement = requirement;
        this.preference = preference;
        this.welfare = welfare;
        this.offerStatus = offerStatus;
        this.submitterCnt = submitterCnt;
        this.headHunterUser = headHunterUser;
        this.company = company;
    }

    public void addJobSeekerUserProposal(JobSeekerUserProposal jobSeekerUserProposal) {
        this.jobSeekerUserProposals.add(jobSeekerUserProposal);
    }

    public void removeJobSeekerUserProposal(JobSeekerUserProposal jobSeekerUserProposal) {
        this.jobSeekerUserProposals.remove(jobSeekerUserProposal);
    }

    public void plusSubmitter() {
        this.submitterCnt++;
    }
    public void changeIntroduction(String introduction) {
        this.selfInterviewQuestion = introduction;
    }
}
