package kimchisoup.matchingjob.entity.dto;

import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.dao.Resume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteResumeForm {
    private String selfIntroduction;
    private CareerExperience careerExperience;
    private String projects;
    private String portfolio;

    private String name;
    private int age;

    private String email;

    private String phoneNumber;
    private String skills;
    private String awards;
    private String link;


    public WriteResumeForm() {
    }

    @Builder
    public WriteResumeForm(String selfIntroduction, CareerExperience careerExperience, String projects, String portfolio, String name, int age, String email, String phoneNumber, String skills, String awards, String link) {
        this.selfIntroduction = selfIntroduction;
        this.careerExperience = careerExperience;
        this.projects = projects;
        this.portfolio = portfolio;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
        this.awards = awards;
        this.link = link;
    }
}
