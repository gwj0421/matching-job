package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.JobSeekerUser;
import kimchisoup.matchingjob.entity.dao.JobSeekerUserCompany;
import kimchisoup.matchingjob.entity.dao.Proposal;
import kimchisoup.matchingjob.error.ErrorMessage;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final SiteUserRepository siteUserRepository;

    @Override
    public ResponseEntity showSubscribedCompaniesProposalsByUserId(String email) {
        Optional<JobSeekerUser> user = siteUserRepository.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(ErrorMessage.WRONG_SITE_USER_BY_EMAIL);
        }
        List<JobSeekerUserCompany> jobSeekerUserCompanies = user.get().getJobSeekerUserCompanies();
        List<Proposal> proposals = new ArrayList<>();
        for (JobSeekerUserCompany jobSeekerUserCompany : jobSeekerUserCompanies) {
            proposals.addAll(jobSeekerUserCompany.getCompany().getProposals());
        }

        return ResponseEntity.ok(proposals);
    }
}
