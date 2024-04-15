package kimchisoup.matchingjob.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimchisoup.matchingjob.config.properties.CoolSMSProperty;
import kimchisoup.matchingjob.repository.*;
import kimchisoup.matchingjob.security.service.EmailService;
import kimchisoup.matchingjob.security.service.EmailServiceImpl;
import kimchisoup.matchingjob.security.service.SMSService;
import kimchisoup.matchingjob.security.service.SMSServiceImpl;
import kimchisoup.matchingjob.service.*;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final SiteUserRepository userRepository;
    private final SuccessfulResumeRepository successfulResumeRepository;
    private final ResumeRepository resumeRepository;
    private final DtoMapper dtoMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final CoolSMSProperty coolSMSProperty;
    private final DefaultMessageService messageService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    SiteUserService siteUserService() {
        return new SiteUserServiceImpl(userRepository, dtoMapper, passwordEncoder);
    }

    @Bean
    ResumeService resumeService() {
        return new ResumeServiceImpl(resumeRepository, siteUserService(), dtoMapper);
    }

    @Bean
    SuccessfulResumeService commonService() {
        return new SuccessfulResumeServiceImpl(successfulResumeRepository);
    }

    @Bean
    SMSService smsService() {
        return new SMSServiceImpl(coolSMSProperty, messageService, siteUserService(), redisService());
    }

    @Bean
    EmailService emailService() {
        return new EmailServiceImpl(javaMailSender,redisService());
    }

    @Bean
    RedisService redisService() {
        return new RedisServiceImpl(stringRedisTemplate);
    }
}
