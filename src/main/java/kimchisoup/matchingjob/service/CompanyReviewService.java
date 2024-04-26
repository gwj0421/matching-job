package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.CompanyReview;
import kimchisoup.matchingjob.entity.dto.CompanyReviewForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;

public interface CompanyReviewService {
    void createCompanyReview(CustomUserDetails userDetails, CompanyReviewForm resumeForm, long companyId);
    void deleteCompanyReview(long reviewId);
    void updateCompanyReview(CustomUserDetails userDetails, CompanyReviewForm companyReviewForm, long companyReviewId, long companyId);
    CompanyReview readCompanyReview(long reviewId);
}
