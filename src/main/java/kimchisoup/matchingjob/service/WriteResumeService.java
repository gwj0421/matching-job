package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.WriteResumeForm;
import kimchisoup.matchingjob.repository.JobSeekerUserRepository;
import kimchisoup.matchingjob.repository.ResumeRepository;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

@Service
public class WriteResumeService {
    private final ResumeRepository resumeRepository;
    private final JobSeekerUserRepository jobSeekerUserRepository;
    public WriteResumeService(ResumeRepository resumeRepository, JobSeekerUserRepository jobSeekerUserRepository){
        this.jobSeekerUserRepository = jobSeekerUserRepository;
        this.resumeRepository = resumeRepository;
    }
    public void create(WriteResumeForm writeResumeForm) {
        Resume resume = new Resume("", CareerExperience.FRESH_MAN,"","","",0,"alswl@naver.com","", EducationField.BACHELOR,"","","",null);
        JobSeekerUser jobSeekerUser = jobSeekerUserRepository.findByEmail(writeResumeForm.getEmail());
        resumeRepository.save(resume.toEntity(writeResumeForm, jobSeekerUser));
    }

    public Resume findUserByEmail(String email) {
        return resumeRepository.findByEmail(email);
    }

    public void delete(Long id) {
        resumeRepository.deleteById(id);
    }
    public void findById(Long id){
        resumeRepository.findById(id);
    }
}
