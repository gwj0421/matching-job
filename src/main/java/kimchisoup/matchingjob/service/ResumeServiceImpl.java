package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.repository.ResumeRepository;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final SiteUserService siteUserService;
    private final DtoMapper dtoMapper;

    @Override
    public void createResume(CustomUserDetails userDetails, ResumeForm resumeForm) {
        JobSeekerUser findUser = (JobSeekerUser) siteUserService.findUserByEmail(userDetails.getUsername()).get();
        Resume savedResume = resumeRepository.save(dtoMapper.toResume(resumeForm,findUser));
        findUser.addResume(savedResume);
    }
}
