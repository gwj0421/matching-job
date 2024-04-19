package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import lombok.Builder;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDate;

@Entity
@Table(name = "RESUME")
@Getter
public class Resume extends BaseTime {

    // todo 취업이미지 서버 내 저장.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private URL idPhoto;
    private LocalDate birthdate;
    private String selfIntroduction;
    @Enumerated(EnumType.STRING)
    private CareerExperience careerExperience;
    private String projects;
    private String portfolio;
    private String name;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private EducationField educationField;
    private String skills;
    private String awards;
    private String link;
    private String certificate;

    public void setIdPhoto(URL idPhoto) {
        this.idPhoto = idPhoto;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public void setCareerExperience(CareerExperience careerExperience) {
        this.careerExperience = careerExperience;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEducationField(EducationField educationField) {
        this.educationField = educationField;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @ManyToOne
    @JoinColumn( name = "job_seeker_id")
    private JobSeekerUser jobSeekerUser;

    public Resume(){}

    @Builder
    public Resume(URL idPhoto, LocalDate birthdate, String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, String name, String email, String phoneNumber, EducationField educationField, String skills, String awards, String link, JobSeekerUser jobSeekerUser, String certificate) {
        this.idPhoto = idPhoto;
        this.birthdate = birthdate;
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.projects = projects;
        this.portfolio = portfolio;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.educationField = educationField;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
        this.jobSeekerUser = jobSeekerUser;
        this.certificate = certificate;
    }

    @Builder
    private Resume(long id, URL idPhoto, LocalDate birthdate, String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, String name, String email, String phoneNumber, EducationField educationField, String skills, String awards, String link, JobSeekerUser jobSeekerUser, String certificate) {
        this.idPhoto = idPhoto;
        this.birthdate = birthdate;
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.projects = projects;
        this.portfolio = portfolio;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.educationField = educationField;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
        this.jobSeekerUser = jobSeekerUser;
        this.certificate = certificate;
    }
}