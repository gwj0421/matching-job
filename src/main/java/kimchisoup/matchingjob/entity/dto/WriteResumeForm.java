package kimchisoup.matchingjob.entity.dto;

import jakarta.validation.constraints.NotBlank;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import kimchisoup.matchingjob.entity.common.InterestField;
import kimchisoup.matchingjob.entity.common.KoreanRegion;
import lombok.Builder;
import java.util.List;

public class WriteResumeForm {
    private String selfIntroduction;
    private CareerExperience careerExperience;
    private String projects;
    private String portfolio;
    @NotBlank
    private String name;
    private int age;
    @NotBlank
    private KoreanRegion residence;
    @NotBlank
    private List<KoreanRegion> prefferedRegion;
    @NotBlank
    private List<InterestField> interestFields;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    private EducationField educationField;
    private String skills;
    private String awards;
    private String link;

    @Builder
    public WriteResumeForm(String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, String name, int age, KoreanRegion residence, List<KoreanRegion> prefferedRegion, List<InterestField> interestFields, String email, String phoneNumber, EducationField educationField, String skills, String awards, String link) {
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.projects = projects;
        this.portfolio = portfolio;
        this.name = name;
        this.age = age;
        this.residence = residence;
        this.prefferedRegion = prefferedRegion;
        this.interestFields = interestFields;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.educationField = educationField;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
    }
}
