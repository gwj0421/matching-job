package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.config.properties.CoolSMSProperty;
import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.security.entity.PhoneAuthForm;
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

import java.util.Optional;

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
    public ResponseEntity sendSMS(String phoneNumber) {
        String certificationNumber = RandomUtils.createCertificationNumber();
        Message message = createMessage(phoneNumber, certificationNumber);
        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("송신 불가");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("확인할 수 없는 에러");
        }
        redisService.setValueWithTTL(phoneNumber, certificationNumber);
        return ResponseEntity.ok().build();
    }

    @Override
    public String verifySMS(PhoneAuthForm phoneAuthForm, BindingResult bindingResult, String errorField) {
        if (!isValidCertificationNumber(phoneAuthForm)) {
            bindingResult.rejectValue(errorField, "error." + errorField, "해당 번호로 등록된 회원정보 없음");
            return null;
        }
        redisService.deleteValue(phoneAuthForm.getPhoneNumber());

        Optional<SiteUser> user = siteUserService.findUserByPhoneNumber(phoneAuthForm.getPhoneNumber());
        if (user.isEmpty()) {
            bindingResult.rejectValue(errorField, "error." + errorField, "해당 번호로 등록된 회원정보 없음");
            return null;
        }
        return user.get().getEmail();
    }

    @Override
    public boolean isValidCertificationNumber(PhoneAuthForm phoneAuthForm) {
        return redisService.hasKey(phoneAuthForm.getPhoneNumber())
                && redisService.getValue(phoneAuthForm.getPhoneNumber()).equals(phoneAuthForm.getInputCode());
    }
}
