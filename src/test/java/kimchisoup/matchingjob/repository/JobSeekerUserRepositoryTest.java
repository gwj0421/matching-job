package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.config.ServiceConfig;
import kimchisoup.matchingjob.entity.common.InterestFieldType;
import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ServiceConfig.class)
class JobSeekerUserRepositoryTest {
    @Autowired
    private JobSeekerUserRepository jobSeekerUserRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private InterestFieldRepository interestFieldRepository;
    @Autowired
    private JobSeekerUserRegionRepository jobSeekerUserRegionRepository;
    @Autowired
    private JobSeekerUserInterestFieldRepository jobSeekerUserInterestFieldRepository;
    @Autowired
    private JobSeekerUserProposalRepository jobSeekerUserProposalRepository;
    @Autowired
    private ProposalRepository proposalRepository;

    @Test
    void crud() throws MalformedURLException {
        // given
        JobSeekerUser user = JobSeekerUser.builder().email("testEmail@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .profileImageUrl(new URL("http://localhost:8080"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(RegionType.DAEJEON)
                .nickName("testNickName")
                .build();

        // when
        JobSeekerUser savedUser = jobSeekerUserRepository.save(user);
        Optional<JobSeekerUser> findUser = jobSeekerUserRepository.findById(savedUser.getId());
        savedUser.changePassword("changedPassword");
        JobSeekerUser changedUser = jobSeekerUserRepository.save(savedUser);
        jobSeekerUserRepository.deleteById(changedUser.getId());
        Optional<JobSeekerUser> checkDelete = jobSeekerUserRepository.findById(savedUser.getId());

        // then
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(findUser).isPresent();
        assertThat(changedUser.getPassword()).isEqualTo("changedPassword");
        assertThat(checkDelete).isNotPresent();
    }

    @Test
    void testMiddleTable() throws MalformedURLException {
        // given
        JobSeekerUser user = JobSeekerUser.builder().email("testEmail@gmail.com")
                .name("testName")
                .githubToken("testGithubToken")
                .profileImageUrl(new URL("http://localhost:8080"))
                .password("testPassword")
                .phoneNumber("testPhoneNumber")
                .residence(RegionType.DAEJEON)
                .nickName("testNickName")
                .build();
        List<RegionType> region = List.of(RegionType.DAEJEON, RegionType.SEOUL);
        List<InterestFieldType> interest = List.of(InterestFieldType.DEVELOPMENT_DATA, InterestFieldType.PLANNING_STRATEGY);

        // when
        JobSeekerUser savedUser = jobSeekerUserRepository.save(user);
        for (RegionType regionType : region) {
            Region savedRegion = regionRepository.save(new Region(regionType));
            JobSeekerUserRegion savedUserRegion = jobSeekerUserRegionRepository.save(new JobSeekerUserRegion(savedUser, savedRegion));
            savedRegion.addJobSeekerUserRegion(savedUserRegion);
            savedUser.addRegion(savedUserRegion);
        }
        for (InterestFieldType interestFieldType : interest) {
            InterestField savedInterestField = interestFieldRepository.save(new InterestField(interestFieldType));
            JobSeekerUserInterestField savedUserInterestField = jobSeekerUserInterestFieldRepository.save(new JobSeekerUserInterestField(savedUser, savedInterestField));
            savedInterestField.addJobSeekerUserInterestField(savedUserInterestField);
            savedUser.addInterestField(savedUserInterestField);
        }
        Optional<JobSeekerUser> findUser = jobSeekerUserRepository.findById(savedUser.getId());
        Optional<List<Region>> regionByDaejeon = regionRepository.findRegionsByRegionType(RegionType.DAEJEON);
        Optional<List<Region>> regionBySeoul = regionRepository.findRegionsByRegionType(RegionType.SEOUL);
        Optional<List<Region>> regionByOther = regionRepository.findRegionsByRegionType(RegionType.NONE);
        Optional<List<InterestField>> interestFieldsByDevelop = interestFieldRepository.findInterestFieldsByInterestFieldType(InterestFieldType.DEVELOPMENT_DATA);
        Optional<List<InterestField>> interestFieldsByPlanning = interestFieldRepository.findInterestFieldsByInterestFieldType(InterestFieldType.PLANNING_STRATEGY);

        // then
        assertThat(findUser).isPresent();
        assertThat(findUser.get().getJobSeekerUserRegions()).hasSize(2);
        assertThat(findUser.get().getJobSeekerUserInterestFields()).hasSize(2);
        assertThat(regionByDaejeon).isPresent();

        assertThat(regionByDaejeon.get()).hasSize(1);
        Region expectedRegionByDeajeon = regionByDaejeon.get().get(0);
        assertThat(expectedRegionByDeajeon.getRegionType()).isEqualTo(RegionType.DAEJEON);
        assertThat(expectedRegionByDeajeon.getJobSeekerUserRegions()).hasSize(1);

        assertThat(regionBySeoul.get()).hasSize(1);
        Region expectedRegionBySeoul = regionBySeoul.get().get(0);
        assertThat(expectedRegionBySeoul.getRegionType()).isEqualTo(RegionType.SEOUL);
        assertThat(expectedRegionBySeoul.getJobSeekerUserRegions()).hasSize(1);
        assertThat(expectedRegionByDeajeon.getJobSeekerUserRegions().get(0).getJobSeekerUser().getId())
                .isEqualTo(expectedRegionBySeoul.getJobSeekerUserRegions().get(0).getJobSeekerUser().getId());
        assertThat(regionByOther.get()).isEmpty();

        assertThat(interestFieldsByDevelop.get()).hasSize(1);
        InterestField expectedInterestFieldByDevelop = interestFieldsByDevelop.get().get(0);
        assertThat(expectedInterestFieldByDevelop.getInterestFieldType()).isEqualTo(InterestFieldType.DEVELOPMENT_DATA);
        assertThat(expectedInterestFieldByDevelop.getJobSeekerUserInterestFields()).hasSize(1);

        assertThat(interestFieldsByPlanning.get()).hasSize(1);
        InterestField expectedInterestFieldByPlanning = interestFieldsByPlanning.get().get(0);
        assertThat(expectedInterestFieldByPlanning.getInterestFieldType()).isEqualTo(InterestFieldType.PLANNING_STRATEGY);
        assertThat(expectedInterestFieldByPlanning.getJobSeekerUserInterestFields()).hasSize(1);
        assertThat(expectedInterestFieldByDevelop.getJobSeekerUserInterestFields().get(0).getJobSeekerUser().getId())
                .isEqualTo(expectedInterestFieldByPlanning.getJobSeekerUserInterestFields().get(0).getJobSeekerUser().getId());
    }
}