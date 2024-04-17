package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    void createResume(CustomUserDetails userDetails, ResumeForm resumeForm);
//    void createResume(ResumeForm resumeForm);
    void deleteResume(long id);
    List<Resume> getData();
    void updateResume(ResumeForm resumeForm);
    Optional<Resume> readResume(long id);
}