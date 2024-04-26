package kimchisoup.matchingjob.entity.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.Company;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class CompanyReviewForm {
    private String title;
    private String advantage;
    private String disadvantage;
    private int starRating;
    private String wish;
    private JobCategory jobCategory;

    public CompanyReviewForm(){}

    @Builder
    public CompanyReviewForm(String title, String advantage, String disadvantage, int starRating, String wish, JobCategory jobCategory){
        this.title = title;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.starRating = starRating;
        this.wish = wish;
        this.jobCategory = jobCategory;
    }
}