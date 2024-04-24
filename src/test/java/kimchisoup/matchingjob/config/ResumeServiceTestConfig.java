package kimchisoup.matchingjob.config;

import kimchisoup.matchingjob.repository.JobFieldRepository;
import kimchisoup.matchingjob.repository.ResumeRepository;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.service.ResumeService;
import kimchisoup.matchingjob.service.ResumeServiceImpl;
import kimchisoup.matchingjob.service.SiteUserService;
import kimchisoup.matchingjob.service.SiteUserServiceImpl;
import kimchisoup.matchingjob.utils.DtoMapper;
import kimchisoup.matchingjob.utils.ProfileImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class ResumeServiceTestConfig {
    @Autowired
    private JobFieldRepository jobFieldRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private SiteUserRepository siteUserRepository;
    @Bean
    public ResumeService resumeService() {
        return new ResumeServiceImpl(resumeRepository, jobFieldRepository, siteUserService(), dtoMapper());
    }

    @Bean
    public SiteUserService siteUserService() {
        return new SiteUserServiceImpl(siteUserRepository,dtoMapper(),passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        };
    }

    @Bean
    public DtoMapper dtoMapper() {
        return new DtoMapper(passwordEncoder(),profileImageUtils());
    }

    @Bean
    public ProfileImageUtils profileImageUtils() {
        return new ProfileImageUtils();
    }
}
