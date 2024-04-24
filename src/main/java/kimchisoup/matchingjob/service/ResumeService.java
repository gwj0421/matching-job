package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.entity.dto.SearchResumeDetailOption;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;

import java.util.List;

public interface ResumeService {
    void createResume(CustomUserDetails userDetails, ResumeForm resumeForm);

    List<Resume> searchResumeByCondition(JobCategory mainJobCategory, Enum subJobCategory, SearchResumeDetailOption searchOption);
}
