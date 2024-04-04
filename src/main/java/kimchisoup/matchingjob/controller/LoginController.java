package kimchisoup.matchingjob.controller;

import kimchisoup.matchingjob.repository.JobSeekerUserRepository;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.entity.dto.SignUpForJobSeekerForm;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final JobSeekerUserRepository jobSeekerUserRepository;
    private final SiteUserRepository userRepository;
    private final DtoMapper dtoMapper;

    @PostMapping("/signUp")
    public ResponseEntity loginByJobSeeker(@RequestPart SignUpForJobSeekerForm user,
                                           @RequestPart(required = false) MultipartFile profileImage) {
        jobSeekerUserRepository.save(dtoMapper.toJobSeekerUser(user, profileImage));
        return ResponseEntity.ok().build();
    }
}
