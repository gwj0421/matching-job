package kimchisoup.matchingjob.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimchisoup.matchingjob.repository.SuccessfulResumeRepository;
import kimchisoup.matchingjob.service.CommonService;
import kimchisoup.matchingjob.service.CommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final SuccessfulResumeRepository successfulResumeRepository;
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommonService commonService() {
        return new CommonServiceImpl(successfulResumeRepository);
    }
}
