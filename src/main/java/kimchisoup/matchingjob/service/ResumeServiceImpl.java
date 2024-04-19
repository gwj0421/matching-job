package kimchisoup.matchingjob.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.repository.ResumeRepository;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.utils.DtoMapper;
import kimchisoup.matchingjob.utils.ProfileImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final SiteUserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final DtoMapper dtoMapper;
    private final EntityManager em;
    private final ProfileImageUtils profileImageUtils;

    @Override
    public void createResume(CustomUserDetails userDetails, ResumeForm resumeForm) {
        JobSeekerUser findUser = (JobSeekerUser) userRepository.findByEmail(userDetails.getUsername()).get();
        Resume savedResume = resumeRepository.save(dtoMapper.toResume(resumeForm, findUser));
        findUser.addResume(savedResume);
    }

    @Override
    public void deleteResume(long id) {
        try {
            resumeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("failed to delete id" + id);
        }
    }

    @Override
    public List<Resume> getData(long id) {
        return resumeRepository.findAllByJobSeekerUser_Id(id);
    }

//    @Override
//    @Transactional
//    public void updateResume(ResumeForm resumeForm, CustomUserDetails userDetails, long resumeId) {
//        JobSeekerUser findUser = (JobSeekerUser) userRepository.findByEmail(userDetails.getUsername()).get();
//        Resume resume = dtoMapper.toResume(resumeForm, findUser);
//        em.find(Resume.class, resumeId);
//        if (resume == null) {
//            // 존재하지 않는 경우 예외 처리 또는 새로운 엔티티 생성 로직 추가
//            System.out.println("resume 없음");
//        }
//        em.merge(resume);
//    }
    @Override
    @Transactional
    public void updateResume(ResumeForm resumeForm, CustomUserDetails userDetails, long resumeId) {
        JobSeekerUser findUser = (JobSeekerUser) userRepository.findByEmail(userDetails.getUsername()).get();
        Resume resume = em.find(Resume.class, resumeId);
        if (resume == null) {
            // 존재하지 않는 경우 예외 처리 또는 새로운 엔티티 생성 로직 추가
            System.out.println("resume 없음");
        }
        resume.setName(resumeForm.getName());
        resume.setAwards(resumeForm.getAwards());
        resume.setLink(resume.getLink());
        resume.setBirthdate(resumeForm.getBirthdate());
        resume.setCareerExperience(resumeForm.getCareerExperience());
        resume.setCertificate(resumeForm.getCertificate());
        resume.setEducationField(resumeForm.getEducationField());
        resume.setEmail(resumeForm.getEmail());
        resume.setIdPhoto(profileImageUtils.saveImageAndReturnUrl(resumeForm.getIdPhoto()));
        resume.setPortfolio(resumeForm.getPortfolio());
        resume.setPhoneNumber(resumeForm.getPhoneNumber());
        resume.setProjects(resumeForm.getProjects());
        resume.setSelfIntroduction(resumeForm.getSelfIntroduction());
        resume.setSkills(resumeForm.getSkills());

        em.merge(resume);
    }

    @Override
    public Optional<Resume> readResume(long id) {
        return resumeRepository.findById(id);
    }
}