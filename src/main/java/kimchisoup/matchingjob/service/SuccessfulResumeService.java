package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.SuccessfulResume;

import java.util.List;

public interface SuccessfulResumeService {
    List<SuccessfulResume> findSuccessfulResumeByCompanyId(Long companyId);
}
