package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.SuccessfulResume;
import kimchisoup.matchingjob.error.EmptySuccessfulResumeException;
import kimchisoup.matchingjob.repository.SuccessfulResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final SuccessfulResumeRepository successfulResumeRepository;

    // todo : 이 후에, DTO에 맞춰 리턴 타입 변경 필요
    @Override
    public List<SuccessfulResume> findSuccessfulResumeByCompanyId(Long companyId) {
        return successfulResumeRepository.findSuccessfulResumesByCompanyId(companyId)
                .orElseThrow(()-> new EmptySuccessfulResumeException("해당 companyId에 맞는 successfulResumes가 없음"));
    }
}
