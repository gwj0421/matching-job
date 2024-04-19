package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    void createResume(CustomUserDetails userDetails, ResumeForm resumeForm);
    void deleteResume(long id);
    List<Resume> getData(long email);
    void updateResume(ResumeForm resumeForm, CustomUserDetails userDetails, long resumeId);
    Optional<Resume> readResume(long id);
}