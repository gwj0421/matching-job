package kimchisoup.matchingjob.entity.dto;

import jakarta.validation.constraints.NotBlank;
import kimchisoup.matchingjob.entity.common.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteProposalForm {
    @NotBlank
    private InterestField interestField;
    @NotBlank
    private CareerExperience requiredMinimumCareerExperience;
    @NotBlank
    private String pay;
    private EducationField requiredMinimumEducationLevel;
    @NotBlank
    private String workType;
    @NotBlank
    private KoreanRegion workRegion;
    @NotBlank
    private String introduction;
    private String requirement;
    private String preference;
    private String welfare;

    @Builder
    public WriteProposalForm(InterestField interestField, CareerExperience requiredMinimumCareerExperience, String pay, EducationField requiredMinimumEducationLevel, String workType, KoreanRegion workRegion, String introduction, String requirement, String preference, String welfare, OfferStatus offerStatus, int submitterCnt) {
        this.interestField = interestField;
        this.requiredMinimumCareerExperience = requiredMinimumCareerExperience;
        this.pay = pay;
        this.requiredMinimumEducationLevel = requiredMinimumEducationLevel;
        this.workType = workType;
        this.workRegion = workRegion;
        this.introduction = introduction;
        this.requirement = requirement;
        this.preference = preference;
        this.welfare = welfare;
    }
}