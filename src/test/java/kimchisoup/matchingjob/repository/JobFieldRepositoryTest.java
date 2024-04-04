package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.common.job.sub.DevelopmentDataSub;
import kimchisoup.matchingjob.entity.dao.JobField;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.JobSeekerUserJobField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobFieldRepositoryTest {
    @Autowired
    private JobFieldRepository jobFieldRepository;
    @Autowired
    private JobSeekerUserRepository jobSeekerUserRepository;
    @Autowired
    private JobSeekerUserJobFieldRepository jobSeekerUserJobFieldRepository;

    @Test
    void Should_findJobField_When_givenMainCategoryAndSubCategory() throws MalformedURLException {
        // given
        JobCategory mainCategory = JobCategory.Development_Data;
        DevelopmentDataSub subCategory = DevelopmentDataSub.BACKEND_DEVELOPER;

        // when
        JobField savedJobField = jobFieldRepository.save(new JobField(mainCategory, subCategory));
        JobSeekerUser user = JobSeekerUser.builder().email("testEmail@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .profileImageUrl(new URL("http://localhost:8080"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(RegionType.DAEJEON)
                .nickName("testNickName")
                .build();
        JobSeekerUser savedUser = jobSeekerUserRepository.save(user);
        JobSeekerUserJobField savedJobSeekerUserJobField = jobSeekerUserJobFieldRepository.save(new JobSeekerUserJobField(savedUser, savedJobField));
        savedJobField.addJobSeekerUserInterestField(savedJobSeekerUserJobField);
        savedUser.addJobField(savedJobSeekerUserJobField);

        // then
        Optional<JobField> findJobField = jobFieldRepository.findJobFieldByJobCategoryAndSubJobCategory(mainCategory, subCategory);
        assertThat(findJobField).isPresent();
        assertThat(findJobField.get().getJobCategory()).isEqualTo(mainCategory);
        assertThat(findJobField.get().getSubJobCategory()).isEqualTo(subCategory);
        assertThat(findJobField.get().getJobSeekerUserJobFields()).hasSize(1);
    }

    @Test
    void Should_findJobFields_When_givenMainCategory() throws MalformedURLException {
        // given
        JobCategory mainCategory = JobCategory.Development_Data;

        // when
        JobField savedJobField1 = jobFieldRepository.save(new JobField(mainCategory, DevelopmentDataSub.BACKEND_DEVELOPER));
        JobField savedJobField2 = jobFieldRepository.save(new JobField(mainCategory, DevelopmentDataSub.FRONTEND_DEVELOPER));
        JobField savedJobField3 = jobFieldRepository.save(new JobField(mainCategory, DevelopmentDataSub.WEB_DEVELOPER));
        JobSeekerUser user = JobSeekerUser.builder().email("testEmail@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .profileImageUrl(new URL("http://localhost:8080"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(RegionType.DAEJEON)
                .nickName("testNickName")
                .build();
        JobSeekerUser savedUser = jobSeekerUserRepository.save(user);
        JobSeekerUserJobField savedJobSeekerUserJobField1 = jobSeekerUserJobFieldRepository.save(new JobSeekerUserJobField(savedUser, savedJobField1));
        JobSeekerUserJobField savedJobSeekerUserJobField2 = jobSeekerUserJobFieldRepository.save(new JobSeekerUserJobField(savedUser, savedJobField2));
        JobSeekerUserJobField savedJobSeekerUserJobField3 = jobSeekerUserJobFieldRepository.save(new JobSeekerUserJobField(savedUser, savedJobField3));
        savedJobField1.addJobSeekerUserInterestField(savedJobSeekerUserJobField1);
        savedJobField2.addJobSeekerUserInterestField(savedJobSeekerUserJobField2);
        savedJobField3.addJobSeekerUserInterestField(savedJobSeekerUserJobField3);
        savedUser.addJobField(savedJobSeekerUserJobField1);
        savedUser.addJobField(savedJobSeekerUserJobField2);
        savedUser.addJobField(savedJobSeekerUserJobField3);

        // then
        Optional<List<JobField>> findJobField = jobFieldRepository.findJobFieldsByJobCategory(mainCategory);
        assertThat(findJobField).isPresent();
        assertThat(findJobField.get()).hasSize(3);
    }

}