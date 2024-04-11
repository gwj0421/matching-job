package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.security.entity.PhoneAuthForm;
import net.nurigo.sdk.message.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface SMSService {
    Message createMessage(String phoneNumber, String content);

    ResponseEntity sendSMSResponse(String phoneNumber);

    String verifySMS(PhoneAuthForm phoneAuthForm, BindingResult bindingResult, String errorField);

    ResponseEntity verifySMSResponse(PhoneAuthForm phoneAuthForm,BindingResult bindingResult);

    boolean isValidCertificationNumber(PhoneAuthForm phoneAuthForm);
}
