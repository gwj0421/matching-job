package kimchisoup.matchingjob.security.service;

import kimchisoup.matchingjob.error.ErrorMessage;
import kimchisoup.matchingjob.security.entity.EmailAuthForm;
import kimchisoup.matchingjob.service.RedisService;
import kimchisoup.matchingjob.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import static kimchisoup.matchingjob.service.RedisServiceImpl.EMAIL_PREFIX;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final RedisService redisService;

    @Override
    public ResponseEntity sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String certificationCode = RandomUtils.createCertificationNumber();
        redisService.setValueWithTTL(EMAIL_PREFIX, email, certificationCode);

        message.setTo(email);
        message.setSubject("matching-job 이메일 인증");
        message.setText(String.format("인증 번호는 [ %s ] 입니다", certificationCode));
        javaMailSender.send(message);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity verifyEmail(EmailAuthForm emailAuthForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !isValidEmail(emailAuthForm)) {
            return ResponseEntity.badRequest().body(ErrorMessage.WRONG_EMAIL_OR_CERTIFICATION_CODE);
        }
        redisService.deleteValue(EMAIL_PREFIX, emailAuthForm.getEmail());
        return ResponseEntity.ok().build();
    }

    @Override
    public boolean isValidEmail(EmailAuthForm emailAuthForm) {
        return redisService.hasKey(EMAIL_PREFIX, emailAuthForm.getEmail()) &&
                redisService.getValue(EMAIL_PREFIX, emailAuthForm.getEmail()).equals(emailAuthForm.getCertificationCode());
    }

    @Override
    public ResponseEntity sendInitPassword(String email,String initPassword) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("matching-job 초기화 비밀번호");
        message.setText(String.format("초기화된 비밀번호는 [ %s ] 입니다", initPassword));
        javaMailSender.send(message);
        return ResponseEntity.ok().build();
    }
}
