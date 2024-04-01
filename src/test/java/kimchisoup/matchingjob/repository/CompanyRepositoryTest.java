package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.config.ServiceConfig;
import kimchisoup.matchingjob.entity.common.Industry;
import kimchisoup.matchingjob.entity.dao.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ServiceConfig.class)
class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;
    @Test
    void crud() {
        // given
        Company company = Company.builder().address("testAddress").sales(BigDecimal.ONE).industry(Industry.INDUSTRIAL_RESTAURANTS)
                .introduction("testIntroduction").peopleCnt(100).build();

        // when
        Company savedCompany = companyRepository.save(company);
        Optional<Company> findCompany = companyRepository.findById(savedCompany.getId());
        savedCompany.changePeopleCnt(150);
        Company changedCompany = companyRepository.save(savedCompany);
        companyRepository.deleteById(savedCompany.getId());
        Optional<Company> checkDelete = companyRepository.findById(savedCompany.getId());

        // then
        assertThat(savedCompany.getName()).isEqualTo(company.getName());
        assertThat(findCompany).isPresent();
        assertThat(changedCompany.getPeopleCnt()).isEqualTo(150);
        assertThat(checkDelete).isNotPresent();
    }
}