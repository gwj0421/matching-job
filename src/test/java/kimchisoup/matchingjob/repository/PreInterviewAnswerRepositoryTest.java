package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.MatchingJobApplication;
import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.dao.HeadHunterUser;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.PreInterviewAnswer;
import kimchisoup.matchingjob.entity.dao.PreInterviewQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = MatchingJobApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PreInterviewAnswerRepositoryTest {
    @Autowired
    private JobSeekerUserRepository jobSeekerUserRepository;
    @Autowired
    private PreInterviewAnswerRepository preInterviewAnswerRepository;
    @Autowired
    private PreInterviewQuestionRepository preInterviewQuestionRepository;
    @Autowired
    private HeadHunterUserRepository headHunterUserRepository;
    @Test
    void findPreInterviewAnswerByJobSeekerUserId() throws MalformedURLException {
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

        PreInterviewQuestion preInterviewQuestion = PreInterviewQuestion.builder()
                .questionText("사전 질문입니다1")
                .headHunterUser(savedHunter)
                .build();

        // 사전 질문 저장
        PreInterviewQuestion savedPreInterviewQuestion = preInterviewQuestionRepository.save(preInterviewQuestion);

        JobSeekerUser user = JobSeekerUser.builder().email("testEmail@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .profileImageUrl(new URL("http://localhost:8090"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(RegionType.DAEJEON)
                .nickName("testNickName")
                .build();
        JobSeekerUser savedJobSeeker = jobSeekerUserRepository.save(user);

        Optional<List<PreInterviewQuestion>>  preInterviewQuestions = preInterviewQuestionRepository.findByHeadHunterUserId(1L);
        PreInterviewQuestion preInterviewQuestion1 = null;
        if (preInterviewQuestions.isPresent()) {
            List<PreInterviewQuestion> preInterviewQuestionsList = preInterviewQuestions.get();
            if (!preInterviewQuestionsList.isEmpty()) {
                preInterviewQuestion1 = preInterviewQuestionsList.get(0);
                // 이제 'preInterviewQuestion' 변수에 첫 번째 PreInterviewQuestion 객체가 들어 있습니다.
            }
        }
        System.out.println(preInterviewQuestion1);

        PreInterviewAnswer preInterviewAnswer = PreInterviewAnswer.builder()
                .preInterviewQuestion(preInterviewQuestion1)
                .jobSeekerUsers(savedJobSeeker)
                .answer("사전 질문 답변입니다.")
                .build();


        PreInterviewAnswer answer = preInterviewAnswerRepository.save(preInterviewAnswer);

        assertThat(answer).isNotNull();

    }
}