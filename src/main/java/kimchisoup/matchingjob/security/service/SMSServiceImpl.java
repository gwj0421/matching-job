package kimchisoup.matchingjob.security.service;

import kimchisoup.matchingjob.config.properties.CoolSMSProperty;
import kimchisoup.matchingjob.error.ErrorMessage;
import kimchisoup.matchingjob.security.entity.PhoneAuthForm;
import kimchisoup.matchingjob.service.RedisService;
import kimchisoup.matchingjob.service.SiteUserService;
import kimchisoup.matchingjob.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import static kimchisoup.matchingjob.service.RedisServiceImpl.SMS_PREFIX;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMSServiceImpl implements SMSService {
    private final CoolSMSProperty coolSMSProperty;
    private final DefaultMessageService messageService;
    private final SiteUserService siteUserService;
    private final RedisService redisService;

    @Override
    public Message createMessage(String phoneNumber, String content) {
        Message message = new Message();
        message.setFrom(coolSMSProperty.getSenderNumber());
        message.setTo(phoneNumber);
        message.setText(String.format("mathcing-job 인증 번호 [ %s ]", content));

        return message;
    }

    @Override
    public ResponseEntity sendSMSResponse(String phoneNumber) {
        if (phoneNumber.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.WRONG_PHONE_NUMBER);
        }
        String certificationCode = RandomUtils.createCertificationNumber();
        Message message = createMessage(phoneNumber, certificationCode);
        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.FAIL_TO_SEND_SMS);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.DEFAULT_ERROR_MESSAGE);
        }
        redisService.setValueWithTTL(SMS_PREFIX, phoneNumber, certificationCode);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity verifySMS(PhoneAuthForm phoneAuthForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !isValidCertificationCode(phoneAuthForm)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.WRONG_PHONE_NUMBER_OR_CERTIFICATION_CODE);
        }
        redisService.deleteValue(SMS_PREFIX, phoneAuthForm.getPhoneNumber());
        return ResponseEntity.ok().build();
    }

    @Override
    public boolean isValidCertificationCode(PhoneAuthForm phoneAuthForm) {
        return redisService.hasKey(SMS_PREFIX, phoneAuthForm.getPhoneNumber())
                && redisService.getValue(SMS_PREFIX, phoneAuthForm.getPhoneNumber()).equals(phoneAuthForm.getCertificationCode());
    }
}
