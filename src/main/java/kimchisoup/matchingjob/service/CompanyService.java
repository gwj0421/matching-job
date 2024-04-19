package kimchisoup.matchingjob.service;

import org.springframework.http.ResponseEntity;

public interface CompanyService {
    ResponseEntity showSubscribedCompaniesProposalsByUserId(String email);
}
