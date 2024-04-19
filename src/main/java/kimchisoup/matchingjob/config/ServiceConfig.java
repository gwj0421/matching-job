package kimchisoup.matchingjob.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import kimchisoup.matchingjob.repository.*;
import kimchisoup.matchingjob.service.ResumeService;
import kimchisoup.matchingjob.service.ResumeServiceImpl;
import kimchisoup.matchingjob.service.SuccessfulResumeService;
import kimchisoup.matchingjob.service.SuccessfulResumeServiceImpl;
import kimchisoup.matchingjob.utils.DtoMapper;
import kimchisoup.matchingjob.utils.ProfileImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final SiteUserRepository userRepository;
    private final SuccessfulResumeRepository successfulResumeRepository;
    private final ResumeRepository resumeRepository;
    private final DtoMapper dtoMapper;
    private final EntityManager em;
    private final ProfileImageUtils profileImageUtils;

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ResumeService resumeService() {
        return new ResumeServiceImpl(userRepository,resumeRepository,dtoMapper,em,profileImageUtils);
    }

    @Bean
    SuccessfulResumeService commonService() {
        return new SuccessfulResumeServiceImpl(successfulResumeRepository);
    }
}
