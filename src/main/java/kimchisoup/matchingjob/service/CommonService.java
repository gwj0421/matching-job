package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.SuccessfulResume;

import java.util.List;

public interface CommonService {
    List<SuccessfulResume> findSuccessfulResumeByCompanyId(Long companyId);
}
