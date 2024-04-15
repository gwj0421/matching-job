package kimchisoup.matchingjob.repository;

import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import kimchisoup.matchingjob.entity.dao.Company;
import kimchisoup.matchingjob.entity.dao.HeadHunterUser;
import kimchisoup.matchingjob.entity.dao.Proposal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ProposalRepositoryTest {
    @Autowired
    private HeadHunterUserRepository headHunterUserRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void crud_withEntityManager() {
        // given
        HeadHunterUser headHunterUser = HeadHunterUser.builder()
                .name("testName")
                .build();
        Company company = Company.builder()
                .name("삼성 전자")
                .build();
        Proposal proposal = Proposal.builder()
                .introduction("원본 소개")
                .headHunterUser(headHunterUser)
                .company(company)
                .build();
        headHunterUserRepository.save(headHunterUser);
        companyRepository.save(company);
        proposalRepository.save(proposal);
        proposal.changeIntroduction("바뀐 소개");

        // when
        entityManager.flush();
        entityManager.clear();
        Optional<Proposal> findProposal = proposalRepository.findById(proposal.getId());
        Optional<HeadHunterUser> findHeadHunter = headHunterUserRepository.findById(headHunterUser.getId());
        Optional<Company> findCompany = companyRepository.findById(company.getId());

        // then
        assertThat(findProposal).isPresent();
        assertThat(findProposal.get().getIntroduction()).isEqualTo("바뀐 소개");
        assertThat(findHeadHunter).isPresent();
        assertThat(findHeadHunter.get().getProposals()).hasSize(1);
        assertThat(findCompany).isPresent();
        assertThat(findCompany.get().getProposals()).hasSize(1);

        // when
        proposalRepository.deleteAll();
        entityManager.flush();
        entityManager.clear();

        // then
        findProposal = proposalRepository.findById(proposal.getId());
        findHeadHunter = headHunterUserRepository.findById(headHunterUser.getId());
        findCompany = companyRepository.findById(company.getId());

        assertThat(findProposal).isNotPresent();
        assertThat(findHeadHunter).isPresent();
        assertThat(findHeadHunter.get().getProposals()).isEmpty();
        assertThat(findCompany).isPresent();
        assertThat(findCompany.get().getProposals()).isEmpty();
    }

    @Test
    void crud() {
        // given
        HeadHunterUser headHunterUser = HeadHunterUser.builder()
                .name("testName")
                .build();
        Company company = Company.builder()
                .name("삼성 전자")
                .build();
        Proposal proposal = Proposal.builder()
                .introduction("원본 소개")
                .headHunterUser(headHunterUser)
                .company(company)
                .build();

        headHunterUserRepository.save(headHunterUser);
        companyRepository.save(company);
        proposalRepository.save(proposal);
        headHunterUser.addProposal(proposal);
        company.addProposal(proposal);

        proposal.changeIntroduction("바뀐 소개");

        // when
        Optional<Proposal> findProposal = proposalRepository.findById(proposal.getId());
        Optional<HeadHunterUser> findHeadHunter = headHunterUserRepository.findById(headHunterUser.getId());
        Optional<Company> findCompany = companyRepository.findById(company.getId());

        // then
        assertThat(findProposal).isPresent();
        assertThat(findProposal.get().getIntroduction()).isEqualTo("바뀐 소개");
        assertThat(findHeadHunter).isPresent();
        assertThat(findHeadHunter.get().getProposals()).hasSize(1);
        assertThat(findCompany).isPresent();
        assertThat(findCompany.get().getProposals()).hasSize(1);

        // when
        proposalRepository.deleteById(proposal.getId());
        headHunterUser.removeProposal(proposal);
        company.removeProposal(proposal);

        // then
        findProposal = proposalRepository.findById(proposal.getId());
        findHeadHunter = headHunterUserRepository.findById(headHunterUser.getId());
        findCompany = companyRepository.findById(company.getId());

        assertThat(findProposal).isNotPresent();
        assertThat(findHeadHunter).isPresent();
        assertThat(findHeadHunter.get().getProposals()).isEmpty();
        assertThat(findCompany).isPresent();
        assertThat(findCompany.get().getProposals()).isEmpty();
    }
}