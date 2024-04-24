package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.config.ResumeServiceTestConfig;
import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.common.EducationField;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.JobField;
import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dao.ResumeJobField;
import kimchisoup.matchingjob.entity.dto.SearchResumeDetailOption;
import kimchisoup.matchingjob.repository.JobFieldRepository;
import kimchisoup.matchingjob.repository.JobSeekerUserRepository;
import kimchisoup.matchingjob.repository.ResumeJobFieldRepository;
import kimchisoup.matchingjob.repository.ResumeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ResumeServiceTestConfig.class)
class ResumeServiceTest {
    @Autowired
    JobSeekerUserRepository jobSeekerUserRepository;
    @Autowired
    JobFieldRepository jobFieldRepository;
    @Autowired
    ResumeJobFieldRepository resumeJobFieldRepository;
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    ResumeServiceImpl resumeServiceImpl;

    @Nested
    class should_SearchResume {
        private JobSeekerUser jobSeekerUser;
        private List<Resume> resumes;
        private List<JobField> jobFields;
        private List<ResumeJobField> resumeJobFields;

        @BeforeEach
        void beforeEach() {
            jobSeekerUser = JobSeekerUser.builder()
                    .name("testName").build();
            resumes = List.of(
                    Resume.builder()
                            .selfIntroduction("안녕하세요. 저는 인내심이 많은 백엔드 개발자입니다. 뽑아주신다면 열심히 하겠습니다.")
                            .careerExperience(CareerExperience.FRESH_MAN)
                            .educationField(EducationField.BACHELOR)
                            .license("정보처리기사")
                            .skills("Spring,Jpa")
                            .jobSeekerUser(jobSeekerUser)
                            .build(),
                    Resume.builder()
                            .selfIntroduction("안녕하세요. 저는 성장한 백엔드 개발자입니다. 뽑아주신다면 열심히 하겠습니다.")
                            .careerExperience(CareerExperience.VETERAN)
                            .educationField(EducationField.BACHELOR)
                            .license("정보처리기사,오픽,OCJP")
                            .skills("Spring,Jpa,WebFlux,Kubernetes")
                            .jobSeekerUser(jobSeekerUser)
                            .build(),
                    Resume.builder()
                            .selfIntroduction("안녕하세요. 저는 웹 개발자입니다. 뽑아주신다면 열심히 하겠습니다.")
                            .careerExperience(CareerExperience.VETERAN)
                            .educationField(EducationField.BACHELOR)
                            .license("정보처리기사")
                            .skills("Spring,kotlin")
                            .jobSeekerUser(jobSeekerUser)
                            .build()
            );
            jobFields = List.of(
                    new JobField(JobCategory.Development_Data, 0),
                    new JobField(JobCategory.Development_Data, 2)
            );
            resumeJobFields = List.of(
                    ResumeJobField.builder()
                            .resume(resumes.get(0))
                            .jobField(jobFields.get(0))
                            .build(),
                    ResumeJobField.builder()
                            .resume(resumes.get(1))
                            .jobField(jobFields.get(0))
                            .build(),
                    ResumeJobField.builder()
                            .resume(resumes.get(2))
                            .jobField(jobFields.get(1))
                            .build()
            );
            jobSeekerUserRepository.save(jobSeekerUser);
            resumeRepository.saveAll(resumes);
            jobFieldRepository.saveAll(jobFields);
            resumeJobFieldRepository.saveAll(resumeJobFields);

            resumes.get(0).addResumeJobFields(resumeJobFields.get(0));
            jobFields.get(0).addResumeJobField(resumeJobFields.get(0));
            resumes.get(1).addResumeJobFields(resumeJobFields.get(1));
            jobFields.get(0).addResumeJobField(resumeJobFields.get(1));
            resumes.get(2).addResumeJobFields(resumeJobFields.get(2));
            jobFields.get(1).addResumeJobField(resumeJobFields.get(2));
        }

        @Test
        void when_givenAllOptions() {
            JobField searchTargetJobField = jobFields.get(0);
            SearchResumeDetailOption searchOption = SearchResumeDetailOption.builder()
                    .careerExperiences(Set.of(CareerExperience.FRESH_MAN, CareerExperience.VETERAN))
                    .educationFields(Set.of(EducationField.BACHELOR))
                    .licenses(Set.of("정보처리기사", "ocjp"))
                    .skills(Set.of("Spring".toLowerCase()))
                    .build();
            List<Resume> searchResult = resumeServiceImpl.searchResumeByCondition(searchTargetJobField.getJobCategory(), searchTargetJobField.getSubJobCategory(), searchOption);
            assertThat(searchResult).hasSize(1);
        }

        @Test
        void when_givenNoOptions() {
            JobField searchTargetJobField = jobFields.get(0);
            SearchResumeDetailOption searchOption = SearchResumeDetailOption.builder()
                    .build();
            List<Resume> searchResult = resumeServiceImpl.searchResumeByCondition(searchTargetJobField.getJobCategory(), searchTargetJobField.getSubJobCategory(), searchOption);
            assertThat(searchResult).hasSize(2);
        }

        @Test
        void when_givenPartOptions() {
            JobField searchTargetJobField = jobFields.get(0);
            SearchResumeDetailOption searchOption = SearchResumeDetailOption.builder()
                    .careerExperiences(Set.of(CareerExperience.FRESH_MAN, CareerExperience.VETERAN))
                    .educationFields(Set.of(EducationField.BACHELOR))
                    .licenses(Set.of("ocjp"))
                    .build();
            List<Resume> searchResult = resumeServiceImpl.searchResumeByCondition(searchTargetJobField.getJobCategory(), searchTargetJobField.getSubJobCategory(), searchOption);
            assertThat(searchResult).hasSize(1);
        }
    }
}