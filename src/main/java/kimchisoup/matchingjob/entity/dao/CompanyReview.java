package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="COMPANY_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CompanyReview extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String advantage;
    private String disadvantage;
    private int starRating;
    private String wish;
    private JobCategory jobCategory;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeekerUser jobSeekerUser;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public void changeDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public void changeStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void changeWish(String wish) {
        this.wish = wish;
    }

    public void changeJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    @Builder
    public CompanyReview(String title, String advantage, String disadvantage, int starRating, String wish, JobCategory jobCategory, JobSeekerUser jobSeekerUser, Company company){
        this.title = title;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.starRating = starRating;
        this.wish = wish;
        this.jobCategory = jobCategory;
        this.jobSeekerUser = jobSeekerUser;
        this.company = company;
    }
}