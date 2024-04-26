package kimchisoup.matchingjob.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kimchisoup.matchingjob.entity.common.Industry;
import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.Company;
import kimchisoup.matchingjob.entity.dao.CompanyReview;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.entity.dto.CompanyReviewForm;
import kimchisoup.matchingjob.repository.CompanyRepository;
import kimchisoup.matchingjob.repository.CompanyReviewRepository;
import kimchisoup.matchingjob.repository.JobSeekerUserRepository;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyReviewServiceTest {
    @Autowired
    private CompanyReviewRepository reviewRepository;
    @Autowired
    private CompanyReviewService reviewService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobSeekerUserRepository jobSeekerUserRepository;

    private CustomUserDetails customUserDetails;
    private CompanyReviewForm companyReviewForm;
    private long companyId;

    @BeforeEach
    @Transactional
    void readyToTest(){
        JobSeekerUser siteUser = JobSeekerUser.builder()
                .name("송민지")
                .email("alswl5436@naver.com")
                .phoneNumber("01012345678")
                .nickName("밍지")
                .residence(RegionType.DAEJEON)
                .build();
        customUserDetails = new CustomUserDetails(siteUser);
        Company company = Company.builder()
                .name("회사A")
                .address("대전광역시 유성구 동서대로125 어딘가")
                .sales(BigDecimal.TEN)
                .introduction("저희 회사는 야근이 없습니다.")
                .industry(Industry.DEVELOPMENT_AND_SUBDIVIDING_OF_REAL_ESTATE)
                .peopleCnt(50)
                .build();
        companyReviewForm = CompanyReviewForm.builder()
                .title("꿈의 기업이에요!")
                .advantage("야근이 없어요")
                .disadvantage("그거 외엔 장점이 없어요 도마ㅇ가,,")
                .jobCategory(JobCategory.HR)
                .starRating(3)
                .wish("부려먹을거면 그만한 금전적 보상을 해주세요")
                .build();
        companyRepository.save(company);
        companyId = company.getId();
        jobSeekerUserRepository.save(siteUser);

        reviewService.createCompanyReview(customUserDetails, companyReviewForm, companyId);
    }

    @Test
    @Transactional
    void createCompanyReview() {
        assertEquals(reviewRepository.findById(1L).orElseThrow().getTitle(), "꿈의 기업이에요!");
    }

    @Test
    @Transactional
    void deleteCompanyReview() {
        reviewService.deleteCompanyReview(1L);

        assertEquals(reviewRepository.count(), 0);
    }

    @Test
    @Transactional
    void updateCompanyReview() {
        CompanyReviewForm updateReviewForm = CompanyReviewForm.builder()
                .title("꿈의 기업이에요!")
                .advantage("돈을 많이줘요")
                .disadvantage("그거 외엔 장점이 없어요 도마ㅇ가,,")
                .jobCategory(JobCategory.HR)
                .starRating(3)
                .wish("야근 없다면서요,,")
                .build();
        System.out.println(updateReviewForm.getAdvantage());
        reviewService.updateCompanyReview(customUserDetails, updateReviewForm, 1L, companyId);

        assertEquals("돈을 많이줘요", reviewRepository.findById(1L).orElseThrow().getAdvantage());
        assertEquals("야근 없다면서요,,", reviewRepository.findById(1L).orElseThrow().getWish());
    }

    @Test
    @Transactional
    void readCompanyReview() {
        CompanyReview companyReview = reviewRepository.findById(1L).orElseThrow();

        assertEquals(companyReview, reviewService.readCompanyReview(1L));

    }
}