package kimchisoup.matchingjob.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimchisoup.matchingjob.repository.SuccessfulResumeRepository;
import kimchisoup.matchingjob.service.SuccessfulResumeService;
import kimchisoup.matchingjob.service.SuccessfulResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final SuccessfulResumeRepository successfulResumeRepository;
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    SuccessfulResumeService commonService() {
        return new SuccessfulResumeServiceImpl(successfulResumeRepository);
    }
}
