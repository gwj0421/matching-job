package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;

public interface ResumeService {
    void createResume(CustomUserDetails userDetails, ResumeForm resumeForm);
}
