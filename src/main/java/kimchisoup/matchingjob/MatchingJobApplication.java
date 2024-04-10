package kimchisoup.matchingjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class MatchingJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatchingJobApplication.class, args);
    }
}
