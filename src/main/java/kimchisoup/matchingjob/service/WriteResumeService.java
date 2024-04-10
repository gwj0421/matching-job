package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.WritingResumeForm;
import kimchisoup.matchingjob.repository.JobSeekerUserRepository;
import kimchisoup.matchingjob.repository.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class WriteResumeService {
    private final ResumeRepository resumeRepository;
    private final JobSeekerUserRepository jobSeekerUserRepository;
    public WriteResumeService(ResumeRepository resumeRepository, JobSeekerUserRepository jobSeekerUserRepository){
        this.jobSeekerUserRepository = jobSeekerUserRepository;
        this.resumeRepository = resumeRepository;
    }
    public void create(WritingResumeForm writingResumeForm) {
        System.out.println(writingResumeForm.getName());
        JobSeekerUser jobSeekerUser = jobSeekerUserRepository.findByEmail(writingResumeForm.getEmail());
        Resume resume = new Resume();
        resumeRepository.save(resume.toEntity(writingResumeForm, jobSeekerUser));
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