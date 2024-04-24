package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dao.ResumeJobField;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.entity.dto.SearchResumeDetailOption;
import kimchisoup.matchingjob.repository.JobFieldRepository;
import kimchisoup.matchingjob.repository.ResumeRepository;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final JobFieldRepository jobFieldRepository;
    private final SiteUserService siteUserService;
    private final DtoMapper dtoMapper;

    @Override
    public void createResume(CustomUserDetails userDetails, ResumeForm resumeForm) {
        JobSeekerUser findUser = (JobSeekerUser) siteUserService.findUserByEmail(userDetails.getUsername()).get();
        Resume savedResume = resumeRepository.save(dtoMapper.toResume(resumeForm, findUser));
        findUser.addResume(savedResume);
    }

    @Override
    public List<Resume> searchResumeByCondition(JobCategory mainJobCategory, Enum subJobCategory, SearchResumeDetailOption searchOption) {
        List<Resume> searchResult = new ArrayList<>();
        List<ResumeJobField> findResumeJobFieldByJobField = jobFieldRepository.findJobFieldByJobCategoryAndSubJobCategory(mainJobCategory, subJobCategory).get().getResumeJobFields();
        for (ResumeJobField resumeJobField : findResumeJobFieldByJobField) {
            Resume _resume = resumeJobField.getResume();
            if (searchOption.searchActivate(_resume)) {
                searchResult.add(_resume);
            }
        }
        return searchResult;
    }
}
