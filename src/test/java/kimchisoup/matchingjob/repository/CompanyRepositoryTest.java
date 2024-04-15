package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void crud() {
        // given
        Company company = Company.builder()
                .peopleCnt(1)
                .introduction("테스트 소개").build();
        companyRepository.save(company);
        company.changePeopleCnt(99);

        // when
        Optional<Company> findCompany = companyRepository.findById(company.getId());
        companyRepository.deleteById(company.getId());
        Optional<Company> checkDelete = companyRepository.findById(company.getId());

        // then
        assertThat(findCompany).isPresent();
        assertThat(findCompany.get().getPeopleCnt()).isEqualTo(99);
        assertThat(checkDelete).isNotPresent();
    }
}