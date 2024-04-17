package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
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
}