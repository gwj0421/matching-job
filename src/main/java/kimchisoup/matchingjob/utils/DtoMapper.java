package kimchisoup.matchingjob.utils;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
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
}
