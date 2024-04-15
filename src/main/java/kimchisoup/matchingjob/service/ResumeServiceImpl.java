package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.repository.ResumeRepository;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final SiteUserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final DtoMapper dtoMapper;

//    @Override
//    public void createResume(CustomUserDetails userDetails, ResumeForm resumeForm) {
//        JobSeekerUser findUser = (JobSeekerUser) userRepository.findByEmail(userDetails.getUsername()).get();
//        Resume savedResume = resumeRepository.save(dtoMapper.toResume(resumeForm,findUser));
//        findUser.addResume(savedResume);
//    }
    @Override
    public void createResume(ResumeForm resumeForm) {
        JobSeekerUser findUser = null;
        Resume savedResume = resumeRepository.save(dtoMapper.toResume(resumeForm,findUser));
//        findUser.addResume(savedResume);
    }

    @Override
    public void deleteResume(long id){
        try{
            resumeRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException ex){
            System.out.println("failed to delete id" + id);
        }
    }

    @Override
    public List<Resume> getData() {
        return resumeRepository.findAll();
    }

    @Override
    public Optional<Resume> findById(long id){
        return resumeRepository.findById(id);
    }
}