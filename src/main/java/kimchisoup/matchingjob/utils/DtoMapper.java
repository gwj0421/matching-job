package kimchisoup.matchingjob.utils;

import kimchisoup.matchingjob.entity.dao.HeadHunterUser;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.SignUpForHeadHunterForm;
import kimchisoup.matchingjob.security.entity.SignUpForJobSeekerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoMapper {
    private final PasswordEncoder passwordEncoder;
    private final ProfileImageUtils profileImageUtils;

    public JobSeekerUser toJobSeekerUser(SignUpForJobSeekerForm form) {
        return JobSeekerUser.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword1()))
                .phoneNumber(form.getPhoneNumber())
                .nickName(form.getNickName())
                .profileImageUrl(profileImageUtils.saveImageAndReturnUrl(form.getProfileImage()))
                .githubToken(form.getGithubToken())
                .residence(form.getResidence())
                .build();
    }

    public HeadHunterUser toHeadHunterUser(SignUpForHeadHunterForm form) {
        return HeadHunterUser.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword1()))
                .phoneNumber(form.getPhoneNumber())
                .nickName(form.getNickName())
                .profileImageUrl(profileImageUtils.saveImageAndReturnUrl(form.getProfileImage()))
                .build();
    }

    public Resume toResume(ResumeForm resumeForm, JobSeekerUser jobSeekerUser) {
        return Resume.builder()
                .idPhoto(profileImageUtils.saveImageAndReturnUrl(resumeForm.getIdPhoto()))
                .birthdate(resumeForm.getBirthdate())
                .selfIntroduction(resumeForm.getSelfIntroduction())
                .careerExperience(resumeForm.getCareerExperience())
                .projects(resumeForm.getProjects())
                .portfolio(resumeForm.getPortfolio())
                .name(resumeForm.getName())
                .email(resumeForm.getEmail())
                .phoneNumber(resumeForm.getPhoneNumber())
                .educationField(resumeForm.getEducationField())
                .skills(resumeForm.getSkills())
                .awards(resumeForm.getAwards())
                .link(resumeForm.getLink())
                .jobSeekerUser(jobSeekerUser)
                .build();
    }
}
