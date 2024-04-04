package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.config.ServiceConfig;
import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ServiceConfig.class)
class SiteUserRepositoryTest {
    @Autowired
    private SiteUserRepository userRepository;

    @Test
    void test() throws MalformedURLException {
        JobSeekerUser user = JobSeekerUser.builder().email("gwj0421@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .profileImageUrl(new URL("http://localhost:8080"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(RegionType.DAEJEON)
                .nickName("testNickName")
                .build();
        userRepository.save(user);
        Optional byEmail = userRepository.findByEmail("gwj0421@gmail.com");
        Assertions.assertThat(byEmail).isPresent();
    }

}