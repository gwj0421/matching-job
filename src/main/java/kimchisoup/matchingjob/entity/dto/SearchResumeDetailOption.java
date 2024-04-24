package kimchisoup.matchingjob.entity.dto;

import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.utils.CustomStringUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchResumeDetailOption {
    private Set<CareerExperience> careerExperiences;
    private Set<EducationField> educationFields;
    private Set<String> licenses;
    private Set<String> skills;

    @Builder
    public SearchResumeDetailOption(Set<CareerExperience> careerExperiences, Set<EducationField> educationFields, Set<String> licenses, Set<String> skills) {
        this.careerExperiences = careerExperiences;
        this.educationFields = educationFields;
        this.licenses = licenses;
        this.skills = skills;
    }

    public boolean searchActivate(Resume resume) {
        return (this.careerExperiences != null ? careerExperiences.contains(resume.getCareerExperience()) : true)
                && (this.educationFields != null ? educationFields.contains(resume.getEducationField()) : true)
                && (this.licenses != null ? CustomStringUtils.convertLowerSet(resume.getLicense()).containsAll(this.licenses) : true)
                && (this.skills != null ? CustomStringUtils.convertLowerSet(resume.getSkills()).containsAll(this.skills) : true);
    }
}
