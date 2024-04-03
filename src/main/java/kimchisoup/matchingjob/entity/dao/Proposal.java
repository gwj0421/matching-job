package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.*;
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
    private InterestFieldType interestFieldType;
    @Enumerated(EnumType.STRING)
    private CareerExperience requiredMinimumCareerExperience;
    private String pay;
    @Enumerated(EnumType.STRING)
    private EducationField requiredMinimumEducationLevel;
    private String workType;
    @Enumerated(EnumType.STRING)
    private RegionType workRegion;
    private String introduction;
    private String requirement;
    private String preference;
    private String welfare;
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;
    private int submitterCnt;

    @ManyToOne
    @JoinColumn(name = "head_hunter_id")
    private HeadHunterUser headHunterUser;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "proposal")
    private List<JobSeekerUserProposal> users = new ArrayList<>();

    @Builder
    public Proposal(InterestFieldType interestFieldType, CareerExperience requiredMinimumCareerExperience, String pay, EducationField requiredMinimumEducationLevel, String workType, RegionType workRegion, String introduction, String requirement, String preference, String welfare, OfferStatus offerStatus, int submitterCnt, HeadHunterUser headHunterUser, Company company) {
        this.interestFieldType = interestFieldType;
        this.requiredMinimumCareerExperience = requiredMinimumCareerExperience;
        this.pay = pay;
        this.requiredMinimumEducationLevel = requiredMinimumEducationLevel;
        this.workType = workType;
        this.workRegion = workRegion;
        this.introduction = introduction;
        this.requirement = requirement;
        this.preference = preference;
        this.welfare = welfare;
        this.offerStatus = offerStatus;
        this.submitterCnt = submitterCnt;
        this.headHunterUser = headHunterUser;
        this.company = company;
    }
}
