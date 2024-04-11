package kimchisoup.matchingjob.service;

import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.security.entity.SignUpForJobSeekerForm;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteUserServiceImpl implements SiteUserService {
    private final SiteUserRepository userRepository;
    private final DtoMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(SignUpForJobSeekerForm signUpForJobSeekerForm) {
        userRepository.save(dtoMapper.toJobSeekerUser(signUpForJobSeekerForm));
    }

    @Override
    public Optional<SiteUser> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<SiteUser> findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<SiteUser> checkPasswordByEmail(String inputPassword, String email, BindingResult bindingResult, String errorField) {
        Optional<SiteUser> findUser = findUserByEmail(email);
        if (findUser.isEmpty()) {
            bindingResult.rejectValue(errorField, "error.email", "해당 이메일은 존재하지 않습니다");
            return Optional.empty();
        }
        if (!passwordEncoder.matches(inputPassword, findUser.get().getPassword())) {
            bindingResult.rejectValue(errorField, "error.password", "이전 비밀번호가 맞지 않습니다");
            return Optional.empty();
        }
        return findUser;
    }

    @Override
    public void checkCertification(boolean certified, BindingResult bindingResult, String errorField) {
        if (!certified) {
            bindingResult.rejectValue(errorField,"error.certified","인증 미실시");
        }
    }

    @Override
    public void checkMatchPassword(String inputPassword, String checkPassword, BindingResult bindingResult, String errorField) {
        if (!inputPassword.equals(checkPassword)) {
            bindingResult.rejectValue(errorField, "error.password", "비밀번호 불일치");
        }
    }

    @Override
    public void checkExistEmail(String email, BindingResult bindingResult, String errorField) {
        if (userRepository.existsByEmail(email)) {
            bindingResult.rejectValue(errorField, "error.email", "존재하는 이메일입니다");
        }
    }

    @Override
    public void changePassword(Optional<SiteUser> user, String changePassword) {
        user.get().changePassword(passwordEncoder.encode(changePassword));
        userRepository.save(user.get());
    }
}
