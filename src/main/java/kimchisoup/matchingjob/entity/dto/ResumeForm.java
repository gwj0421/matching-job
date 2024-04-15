package kimchisoup.matchingjob.entity.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@Getter
public class ResumeForm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private MultipartFile idPhoto;
    @NotEmpty
    private String name;
    private LocalDate birthdate;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String selfIntroduction;
    private CareerExperience careerExperience;
    private EducationField educationField;
    @NotEmpty
    private String portfolio;
    private String projects;
    private String skills;
    private String certificate;
    private String awards;
    private String link;


    public ResumeForm() {
    }

    @Builder
    public ResumeForm(MultipartFile idPhoto, String name, LocalDate birthdate, String email, String phoneNumber, String selfIntroduction, CareerExperience careerExperience, EducationField educationField, String portfolio, String projects, String skills, String certificate, String awards, String link) {
        this.idPhoto = idPhoto;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.educationField = educationField;
        this.portfolio = portfolio;
        this.projects = projects;
        this.skills = skills;
        this.certificate = certificate;
        this.awards = awards;
        this.link = link;
    }
}
