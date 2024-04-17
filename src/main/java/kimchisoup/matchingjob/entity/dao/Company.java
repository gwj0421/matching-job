package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.Industry;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int peopleCnt;
    private BigDecimal sales;
    private String address;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private String introduction;

    @OneToMany(mappedBy = "company")
    private List<Proposal> proposals = new ArrayList<>();
    @OneToMany(mappedBy = "company")
    private List<SuccessfulResume> successfulResumes = new ArrayList<>();
    @OneToMany(mappedBy ="company")
    private List<JobSeekerUserCompany> subscribedJobSeekerUser = new ArrayList<>();

    @Builder
    public Company(String name, int peopleCnt, BigDecimal sales, String address, Industry industry, String introduction) {
        this.name = name;
        this.peopleCnt = peopleCnt;
        this.sales = sales;
        this.address = address;
        this.industry = industry;
        this.introduction = introduction;
    }

    public void changePeopleCnt(int peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public void addProposal(Proposal proposal) {
        this.proposals.add(proposal);
    }

    public void removeProposal(Proposal proposal) {
        this.proposals.remove(proposal);
    }

    public void addSuccessfulResume(SuccessfulResume successfulResume) {
        this.successfulResumes.add(successfulResume);
    }

    public void removeSuccessfulResume(SuccessfulResume successfulResume) {
        this.successfulResumes.remove(successfulResume);
    }

    public void addSubscribedJobSeekerUser(JobSeekerUserCompany subscribedJobSeekerUser) {
        this.subscribedJobSeekerUser.add(subscribedJobSeekerUser);
    }

    public void removeSubscribedJobSeekerUser(JobSeekerUserCompany subscribedJobSeekerUser) {
        this.subscribedJobSeekerUser.remove(subscribedJobSeekerUser);
    }
}
