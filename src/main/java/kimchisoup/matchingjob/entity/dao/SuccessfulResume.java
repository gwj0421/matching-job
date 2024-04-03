package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUCCESSFUL_SUBMITTER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SuccessfulResume extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String selfIntroduction;
    @Enumerated(EnumType.STRING)
    private CareerExperience careerExperience;
    private String projects;
    private String portfolio;
    private int age;
    @Enumerated(EnumType.STRING)
    private EducationField educationField;
    private String skills;
    private String awards;
    private String link;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Company company;

    @Builder
    public SuccessfulResume(String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, int age, EducationField educationField, String skills, String awards, String link, Company company) {
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.projects = projects;
        this.portfolio = portfolio;
        this.age = age;
        this.educationField = educationField;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
        this.company = company;
    }
}
