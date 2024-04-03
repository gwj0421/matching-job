package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.config.ServiceConfig;
import kimchisoup.matchingjob.entity.common.InterestField;
import kimchisoup.matchingjob.entity.common.KoreanRegion;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ServiceConfig.class)
class JobSeekerUserRepositoryTest {
    @Autowired
    private JobSeekerUserRepository jobSeekerUserRepository;
    @Test
    void crud() throws MalformedURLException {
        // given
        JobSeekerUser user = JobSeekerUser.builder().email("testEmail@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .interestFields(List.of(InterestField.FOOD_BEVERAGE, InterestField.FINANCE_INSURANCE))
                .profileImageUrl(new URL("http://localhost:8080"))
                .preferredRegion(List.of(KoreanRegion.DAEJEON, KoreanRegion.GYEONGGI))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(KoreanRegion.DAEJEON)
                .nickName("testNickName")
                .build();

        // when
        JobSeekerUser savedUser = jobSeekerUserRepository.save(user);
        Optional<JobSeekerUser> findUser = jobSeekerUserRepository.findById(savedUser.getId());
        savedUser.changePassword("changedPassword");
        JobSeekerUser changedUser = jobSeekerUserRepository.save(savedUser);
        jobSeekerUserRepository.deleteById(changedUser.getId());
        Optional<JobSeekerUser> checkDelete = jobSeekerUserRepository.findById(savedUser.getId());

        // then
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(findUser).isPresent();
        assertThat(changedUser.getPassword()).isEqualTo("changedPassword");
        assertThat(checkDelete).isNotPresent();
    }
}