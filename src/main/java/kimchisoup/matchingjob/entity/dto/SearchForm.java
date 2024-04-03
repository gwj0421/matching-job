package kimchisoup.matchingjob.entity.dto;

import kimchisoup.matchingjob.entity.common.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchForm {
    private String searchingword;
    private CompanyType companyType;
    private EducationField educationField;
    private Industry industry;
    private OfferStatus offerStatus;
    private RegionType regionType;
    private CareerExperience careerExperience;

    @Builder
    public SearchForm(String searchingword, CompanyType companyType, EducationField educationField, Industry industry, OfferStatus offerStatus, RegionType regionType, CareerExperience careerExperience) {
        this.searchingword = searchingword;
        this.companyType = companyType;
        this.educationField = educationField;
        this.industry = industry;
        this.offerStatus = offerStatus;
        this.regionType = regionType;
        this.careerExperience = careerExperience;
    }
}
