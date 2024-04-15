package kimchisoup.matchingjob.security.service;

import kimchisoup.matchingjob.security.entity.EmailAuthForm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface EmailService {
    ResponseEntity sendEmail(String email);

    ResponseEntity verifyEmail(EmailAuthForm emailAuthForm, BindingResult bindingResult);

    boolean isValidEmail(EmailAuthForm emailAuthForm);

    ResponseEntity sendInitPassword(String email, String initPassword);
}
