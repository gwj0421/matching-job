package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESUME")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Resume extends BaseTime {
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
    private String license;
    private String skills;
    private String awards;
    private String link;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeekerUser jobSeekerUser;
    @OneToMany(mappedBy = "resume")
    private List<ResumeJobField> resumeJobFields = new ArrayList<>();

    @Builder
    public Resume(URL idPhoto, LocalDate birthdate, String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, String name, String email, String phoneNumber, EducationField educationField, String license, String skills, String awards, String link, JobSeekerUser jobSeekerUser) {
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
        this.license = license;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
        this.jobSeekerUser = jobSeekerUser;
    }

    public void addResumeJobFields(ResumeJobField resumeJobField) {
        this.resumeJobFields.add(resumeJobField);
    }

    public void removeResumeJobFields(ResumeJobField resumeJobField) {
        this.resumeJobFields.remove(resumeJobField);
    }
}
