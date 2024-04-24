package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import kimchisoup.matchingjob.entity.dao.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MiddleTableTest {
    @Autowired
    private JobSeekerUserRepository jobSeekerUserRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private JobFieldRepository jobFieldRepository;
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobSeekerUserRegionRepository jobSeekerUserRegionRepository;
    @Autowired
    private JobSeekerUserJobFieldRepository jobSeekerUserJobFieldRepository;
    @Autowired
    private JobSeekerUserProposalRepository jobSeekerUserProposalRepository;
    @Autowired
    private JobSeekerUserCompanyRepository jobSeekerUserCompanyRepository;

    @Nested
    @DisplayName("crud by ")
    class crudBy {
        @Test
        @DisplayName("JobSeekerUserRegion")
        void jobSeekerUserRegion() {
            JobSeekerUser user = JobSeekerUser.builder()
                    .name("testName").build();
            Region region = Region.builder()
                    .regionType(RegionType.DAEJEON).build();
            jobSeekerUserRepository.save(user);
            regionRepository.save(region);

            // when
            JobSeekerUserRegion jobSeekerUserRegion = JobSeekerUserRegion.builder()
                    .jobSeekerUser(user).region(region).build();
            jobSeekerUserRegionRepository.save(jobSeekerUserRegion);
            user.addJobSeekerUserRegion(jobSeekerUserRegion);
            region.addJobSeekerUserRegion(jobSeekerUserRegion);
            region.changeRegionType(RegionType.SEOUL);

            // then
            Optional<JobSeekerUserRegion> findJobSeekerUserRegion = jobSeekerUserRegionRepository.findById(jobSeekerUserRegion.getId());
            assertCreateReadMiddleTable(
                    jobSeekerUserRepository.findById(user.getId()),
                    regionRepository.findById(region.getId()),
                    findJobSeekerUserRegion
                    );
            assertThat(findJobSeekerUserRegion.get().getRegion().getRegionType()).isEqualTo(RegionType.SEOUL);

            // when
            jobSeekerUserRegionRepository.deleteById(jobSeekerUserRegion.getId());
            user.removeJobSeekerUserRegion(jobSeekerUserRegion);
            region.removeJobSeekerUserRegion(jobSeekerUserRegion);

            // then
            assertAfterDeleteMiddleTable(
                    jobSeekerUserRepository.findById(user.getId()),
                    regionRepository.findById(region.getId()),
                    jobSeekerUserRegionRepository.findById(jobSeekerUserRegion.getId()),
                    JobSeekerUserRegion.class);
        }

        @Test
        @DisplayName("JobSeekerUserJobField")
        void jobSeekerUserJobField() {
            // given
            JobSeekerUser user = JobSeekerUser.builder()
                    .name("testName").build();
            JobField jobField = new JobField(JobCategory.Development_Data, 0);
            jobSeekerUserRepository.save(user);
            jobFieldRepository.save(jobField);

            // when
            JobSeekerUserJobField jobSeekerUserJobField = JobSeekerUserJobField.builder()
                    .jobSeekerUser(user)
                    .jobField(jobField)
                    .build();
            jobSeekerUserJobFieldRepository.save(jobSeekerUserJobField);
            user.addJobSeekerUserJobFields(jobSeekerUserJobField);
            jobField.addJobSeekerJobField(jobSeekerUserJobField);

            // then
            Optional<JobSeekerUserJobField> findJobSeekerUserJobField = jobSeekerUserJobFieldRepository.findById(jobSeekerUserJobField.getId());
            assertCreateReadMiddleTable(jobSeekerUserRepository.findById(user.getId()),
                    jobFieldRepository.findById(jobField.getId()),
                    findJobSeekerUserJobField
            );

            // when
            jobSeekerUserJobFieldRepository.deleteById(jobSeekerUserJobField.getId());
            user.removeJobSeekerUserJobFields(jobSeekerUserJobField);
            jobField.removeJobSeekerJobField(jobSeekerUserJobField);

            // then
            assertAfterDeleteMiddleTable(jobSeekerUserRepository.findById(user.getId()),
                    jobFieldRepository.findById(jobField.getId()),
                    jobSeekerUserJobFieldRepository.findById(jobSeekerUserJobField.getId()),
                    JobSeekerUserJobField.class);
        }

        @Test
        @DisplayName("JobSeekerUserProposals")
        void jobSeekerUserProposals() {
            // given
            JobSeekerUser user = JobSeekerUser.builder()
                    .name("testName").build();
            Proposal proposal = Proposal.builder()
                    .jobCategory(JobCategory.Development_Data)
                    .subJobCategoryIdx(0)
                    .selfInterviewQuestion("당신의 꿈은 무엇입니까")
                    .build();
            jobSeekerUserRepository.save(user);
            proposalRepository.save(proposal);

            // when
            JobSeekerUserProposal jobSeekerUserProposal = JobSeekerUserProposal.builder()
                    .jobSeekerUser(user)
                    .proposal(proposal)
                    .selfInterviewAnswer("내 꿈은 우주비행사")
                    .build();
            jobSeekerUserProposalRepository.save(jobSeekerUserProposal);
            user.addJobSeekerUserProposal(jobSeekerUserProposal);
            proposal.addJobSeekerUserProposal(jobSeekerUserProposal);

            // then
            Optional<JobSeekerUserProposal> findJobSeekerUserProposal = jobSeekerUserProposalRepository.findById(jobSeekerUserProposal.getId());
            assertCreateReadMiddleTable(jobSeekerUserRepository.findById(user.getId()),
                    proposalRepository.findById(proposal.getId()),
                    findJobSeekerUserProposal
            );

            // when
            jobSeekerUserProposalRepository.deleteById(jobSeekerUserProposal.getId());
            user.removeJobSeekerUserProposal(jobSeekerUserProposal);
            proposal.removeJobSeekerUserProposal(jobSeekerUserProposal);

            // then
            assertAfterDeleteMiddleTable(jobSeekerUserRepository.findById(user.getId()),
                    proposalRepository.findById(proposal.getId()),
                    jobSeekerUserProposalRepository.findById(jobSeekerUserProposal.getId()),
                    JobSeekerUserProposal.class);
        }
        @Test
        @DisplayName("JobSeekerUserCompany")
        void jobSeekerUserCompany() {
            // given
            JobSeekerUser user = JobSeekerUser.builder()
                    .name("testName").build();
            Company company = Company.builder()
                    .peopleCnt(1)
                    .introduction("테스트 소개").build();
            jobSeekerUserRepository.save(user);
            companyRepository.save(company);
            JobSeekerUserCompany jobSeekerUserCompany = JobSeekerUserCompany.builder()
                    .jobSeekerUser(user)
                    .company(company)
                    .build();

            // when
            jobSeekerUserCompanyRepository.save(jobSeekerUserCompany);
            user.addSubscribedCompany(jobSeekerUserCompany);
            company.addSubscribedJobSeekerUser(jobSeekerUserCompany);
            company.changePeopleCnt(99);

            // then
            Optional<JobSeekerUserCompany> findJobSeekerUserCompany = jobSeekerUserCompanyRepository.findById(jobSeekerUserCompany.getId());
            assertCreateReadMiddleTable(jobSeekerUserRepository.findById(user.getId()),
                    companyRepository.findById(company.getId()),
                    findJobSeekerUserCompany
                    );
            assertThat(findJobSeekerUserCompany.get().getCompany().getPeopleCnt()).isEqualTo(99);

            // when
            jobSeekerUserCompanyRepository.deleteById(jobSeekerUserCompany.getId());
            user.removeSubscribedCompany(jobSeekerUserCompany);
            company.removeSubscribedJobSeekerUser(jobSeekerUserCompany);

            // then
            assertAfterDeleteMiddleTable(jobSeekerUserRepository.findById(user.getId()),
                    companyRepository.findById(company.getId()),
                    jobSeekerUserCompanyRepository.findById(jobSeekerUserCompany.getId()),
                    JobSeekerUserCompany.class);
        }
    }

    private static void assertCreateReadMiddleTable(Optional leftTable, Optional rightTable, Optional middleTable) {
        assertThat(leftTable).isPresent();
        assertThat(rightTable).isPresent();
        assertThat(middleTable).isPresent();
        if (middleTable.get() instanceof JobSeekerUserRegion) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserRegions()).hasSize(1);
            assertThat(((Region) rightTable.get()).getJobSeekerUserRegions()).hasSize(1);
        } else if (middleTable.get() instanceof JobSeekerUserJobField) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserJobFields()).hasSize(1);
            assertThat(((JobField) rightTable.get()).getJobSeekerUserJobFields()).hasSize(1);
        } else if (middleTable.get() instanceof JobSeekerUserProposal) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserProposals()).hasSize(1);
            assertThat(((Proposal) rightTable.get()).getJobSeekerUserProposals()).hasSize(1);
        } else if (middleTable.get() instanceof JobSeekerUserCompany) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserCompanies()).hasSize(1);
            assertThat(((Company) rightTable.get()).getSubscribedJobSeekerUser()).hasSize(1);
        }
    }

    private static void assertAfterDeleteMiddleTable(Optional leftTable, Optional rightTable, Optional middleTable, Class middleTableClass) {
        assertThat(leftTable).isPresent();
        assertThat(rightTable).isPresent();
        assertThat(middleTable).isEmpty();
        if (middleTableClass == JobSeekerUserRegion.class) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserRegions()).isEmpty();
            assertThat(((Region) rightTable.get()).getJobSeekerUserRegions()).isEmpty();
        } else if (middleTableClass == JobSeekerUserJobField.class) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserJobFields()).isEmpty();
            assertThat(((JobField) rightTable.get()).getJobSeekerUserJobFields()).isEmpty();
        } else if (middleTableClass == JobSeekerUserProposal.class) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserProposals()).isEmpty();
            assertThat(((Proposal) rightTable.get()).getJobSeekerUserProposals()).isEmpty();
        } else if (middleTableClass == JobSeekerUserCompany.class) {
            assertThat(((JobSeekerUser) leftTable.get()).getJobSeekerUserCompanies()).isEmpty();
            assertThat(((Company) rightTable.get()).getSubscribedJobSeekerUser()).isEmpty();
        }
    }
}
