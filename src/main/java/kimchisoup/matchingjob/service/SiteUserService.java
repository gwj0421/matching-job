package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.security.entity.SignUpForJobSeekerForm;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface SiteUserService {
    void saveUser(SignUpForJobSeekerForm signUpForJobSeekerForm);

    Optional<SiteUser> findUserByEmail(String email);

    Optional<SiteUser> findUserByPhoneNumber(String phoneNumber);

    Optional<SiteUser> checkPasswordByEmail(String inputPassword, String email, BindingResult bindingResult, String errorField);

    void checkCertification(boolean certified, BindingResult bindingResult, String errorField);

    void checkMatchPassword(String inputPassword, String checkPassword, BindingResult bindingResult, String errorField);

    void checkExistEmail(String email, BindingResult bindingResult, String errorField);

    void changePassword(Optional<SiteUser> user, String changePassword);
}
