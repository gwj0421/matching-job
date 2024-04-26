package kimchisoup.matchingjob.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kimchisoup.matchingjob.entity.dao.Company;
import kimchisoup.matchingjob.entity.dao.CompanyReview;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dto.CompanyReviewForm;
import kimchisoup.matchingjob.repository.CompanyRepository;
import kimchisoup.matchingjob.repository.CompanyReviewRepository;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyReviewServiceImpl implements CompanyReviewService{
    private final CompanyReviewRepository companyReviewRepository;
    private final SiteUserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final DtoMapper dtoMapper;
    private final EntityManager em;

    @Override
    public void createCompanyReview(CustomUserDetails userDetails, CompanyReviewForm companyReviewForm, long companyId) {
        JobSeekerUser findUser = (JobSeekerUser) userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        Company findCompany = companyRepository.findById(companyId).orElseThrow();
        CompanyReview companyReview = dtoMapper.toCompanyReivew(companyReviewForm, findUser, findCompany);

        companyReviewRepository.save(companyReview);

        findUser.addCompanyReivew(companyReview);
        findCompany.addCompanyReview(companyReview);
    }

    @Override
    public void deleteCompanyReview(long reviewId) {
        companyReviewRepository.deleteById(reviewId);
    }

    @Override
    @Transactional
    public void updateCompanyReview(CustomUserDetails userDetails, CompanyReviewForm companyReviewForm, long companyReviewId, long companyId) {
        System.out.println(companyReviewForm.getAdvantage());
        CompanyReview companyReview = em.find(CompanyReview.class, companyReviewId);

        companyReview.changeTitle(companyReviewForm.getTitle());
        companyReview.changeAdvantage(companyReviewForm.getAdvantage());
        companyReview.changeDisadvantage(companyReview.getDisadvantage());
        companyReview.changeWish(companyReviewForm.getWish());
        companyReview.changeStarRating(companyReviewForm.getStarRating());
        companyReview.changeJobCategory(companyReviewForm.getJobCategory());

        em.merge(companyReview);
    }

    @Override
    public CompanyReview readCompanyReview(long reviewId) {
        return companyReviewRepository.findById(reviewId).orElseThrow();
    }
}
