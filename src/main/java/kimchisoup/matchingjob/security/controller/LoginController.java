package kimchisoup.matchingjob.security.controller;

import jakarta.validation.Valid;
import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.repository.SiteUserRepository;
import kimchisoup.matchingjob.security.entity.*;
import kimchisoup.matchingjob.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final SiteUserRepository userRepository;
    private final DtoMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(@RequestParam(value = "errorCode", required = false) Integer errorCode,
                        Model model) {
        model.addAttribute("errorCode", errorCode);
        return "LoginForm";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("signUpForm", new SignUpForJobSeekerForm());
        return "SignUpForm";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute(name = "signUpForm") SignUpForJobSeekerForm signUpForm,
                         BindingResult bindingResult,
                         Model model) {
        if (!signUpForm.getPassword1().equals(signUpForm.getPassword2())) {
            bindingResult.rejectValue("password2", "error.password2", "비밀번호 불일치");
        }
        if (userRepository.existsByEmail(signUpForm.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "존재하는 이메일입니다");
        }

        if (bindingResult.hasErrors()) {
            return "SignUpForm";
        }
        userRepository.save(dtoMapper.toJobSeekerUser(signUpForm));

        return "redirect:/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "ChangePasswordForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/changePassword")
    public String changePassword(@AuthenticationPrincipal CustomUserDetails user,
                                    @Valid @ModelAttribute(name = "changePasswordForm") ChangePasswordForm changePasswordForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getNewPasswordForCheck())) {
            bindingResult.rejectValue("newPasswordForCheck", "error.newPasswordForCheck", "비밀번호 불일치");
        }
        SiteUser findUser = (SiteUser) userRepository.findByEmail(user.getUsername()).get();
        if (!passwordEncoder.matches(changePasswordForm.getPassword(), findUser.getPassword())) {
            bindingResult.rejectValue("password", "error.password", "이전 비밀번호가 맞지 않습니다");
        }
        if (bindingResult.hasErrors()) {
            return "ChangePasswordForm";
        }
        findUser.changePassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userRepository.save(findUser);

        return "redirect:/";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sms-certification")
    public String sendSMS(Model model) {
        model.addAttribute("resetPasswordAuthForm", new ResetPasswordAuthForm());
        return "ResetPassword";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sms-certification/send")
    @ResponseBody
    public String sendSMS(String phoneNumber, Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        SMSRequest requestBody = new SMSRequest(SMSType.SMS.name(), "01034754617", "textContent", "01077524617");

        new HttpEntity<>(requestBody, headers);

//        Employee employee = restTemplate.postForObject(String.format(apiUrl,), request, Employee.class);
        return "ResetPassword";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sms-certification/confirm")
    public String confirmSMS(@Valid ResetPasswordAuthForm resetPasswordAuthForm,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            return "/sms-certification/confirm";
        }
        log.info("gwj confirmSMS");
        return "redirect:/login";
    }
}
