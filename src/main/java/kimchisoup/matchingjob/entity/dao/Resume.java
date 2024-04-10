package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import kimchisoup.matchingjob.entity.dto.WritingResumeForm;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "RESUME")
@Getter
public class Resume extends BaseTime {

    // todo 취업이미지 서버 내 저장.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String selfIntroduction;
    @Enumerated(EnumType.STRING)
    private CareerExperience careerExperience;
    private String projects;
    private String portfolio;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private EducationField educationField;
    private String skills;
    private String awards;
    private String link;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeekerUser jobSeekerUser;

    public Resume(){}

    @Builder
    public Resume(String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, String name, int age, String email, String phoneNumber, EducationField educationField, String skills, String awards, String link, JobSeekerUser jobSeekerUser) {
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.projects = projects;
        this.portfolio = portfolio;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.educationField = educationField;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
        this.jobSeekerUser = jobSeekerUser;
    }

    public Resume toEntity(WritingResumeForm dto, JobSeekerUser jobSeekerUser){
        return Resume.builder()
                .selfIntroduction(dto.getSelfIntroduction())
                .careerExperience(dto.getCareerExperience())
                .projects(dto.getProjects())
                .portfolio(dto.getPortfolio())
                .name(dto.getName())
                .age(dto.getAge())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .educationField(getEducationField())
                .skills(dto.getSkills())
                .awards(dto.getAwards())
                .link(dto.getLink())
                .jobSeekerUser(jobSeekerUser).build();
    }
}