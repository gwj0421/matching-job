package kimchisoup.matchingjob.security.service;

import kimchisoup.matchingjob.security.entity.PhoneAuthForm;
import net.nurigo.sdk.message.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface SMSService {
    Message createMessage(String phoneNumber, String content);

    ResponseEntity sendSMSResponse(String phoneNumber);

    ResponseEntity verifySMS(PhoneAuthForm phoneAuthForm, BindingResult bindingResult);

    boolean isValidCertificationCode(PhoneAuthForm phoneAuthForm);
}
