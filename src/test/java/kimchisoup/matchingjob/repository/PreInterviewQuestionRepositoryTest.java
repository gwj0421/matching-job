package kimchisoup.matchingjob.repository;

import groovy.util.logging.Slf4j;
import kimchisoup.matchingjob.MatchingJobApplication;
import kimchisoup.matchingjob.config.ServiceConfig;
import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.dao.HeadHunterUser;
import kimchisoup.matchingjob.entity.dao.PreInterviewQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = MatchingJobApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PreInterviewQuestionRepositoryTest {
    @Autowired
    private PreInterviewQuestionRepository preInterviewQuestionRepository;
    @Autowired
    private HeadHunterUserRepository headHunterUserRepository;
    @Test
    void findPreInterviewQuestionsByHeadHunterId() throws MalformedURLException {
        // 헤드헌터 사용자 생성
        HeadHunterUser headHunterUser = HeadHunterUser.builder()
                .email("testEmail@gmail.com")
                .name("testName")
                .profileImageUrl(new URL("https://www.google.co.kr/logos/doodles/2024/temp-south-korea-legislative-elections-2024-6753651837110483-l.webp"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .build();
        // 헤드헌터 사용자 저장
        HeadHunterUser savedHunter = headHunterUserRepository.save(headHunterUser);

        System.out.println(headHunterUser.getEmail());

        // 사전 질문 생성 및 헤드헌터 설정
        PreInterviewQuestion preInterviewQuestion = PreInterviewQuestion.builder()
                .questionText("사전 질문입니다1")
                .headHunterUser(savedHunter)
                .build();

        // 사전 질문 저장
        PreInterviewQuestion savedPreInterviewQuestion = preInterviewQuestionRepository.save(preInterviewQuestion);

        // 저장된 사전 질문이 null이 아닌지 확인
        assertThat(savedPreInterviewQuestion).isNotNull();

    }
}