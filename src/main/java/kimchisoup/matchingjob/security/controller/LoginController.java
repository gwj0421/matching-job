package kimchisoup.matchingjob.security.controller;

import jakarta.validation.Valid;
import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.security.entity.ChangePasswordForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.security.entity.PhoneAuthForm;
import kimchisoup.matchingjob.security.entity.SignUpForJobSeekerForm;
import kimchisoup.matchingjob.service.SMSService;
import kimchisoup.matchingjob.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final SMSService smsService;
    private final SiteUserService siteUserService;

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
        siteUserService.checkMatchPassword(signUpForm.getPassword1(), signUpForm.getPassword2(), bindingResult, "password2");
        siteUserService.checkExistEmail(signUpForm.getEmail(),bindingResult,"email");

        if (bindingResult.hasErrors()) {
            return "SignUpForm";
        }
        siteUserService.saveUser(signUpForm);

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
        siteUserService.checkMatchPassword(changePasswordForm.getNewPassword(),changePasswordForm.getNewPasswordForCheck(),bindingResult,"newPasswordForCheck");
        Optional<SiteUser> findUser = siteUserService.checkPasswordByEmail(changePasswordForm.getPassword(), user.getUsername(), bindingResult, "password");

        if (bindingResult.hasErrors()) {
            return "ChangePasswordForm";
        }

        siteUserService.changePassword(findUser, changePasswordForm.getNewPassword());
        return "redirect:/";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/findId")
    public String sendSMS(Model model) {
        model.addAttribute("phoneAuthForm", new PhoneAuthForm());
        return "FindEmail";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sms-certification/send")
    @ResponseBody
    public ResponseEntity sendSMS(String phoneNumber, Model model) {
        return smsService.sendSMS(phoneNumber);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sms-certification/confirm")
    public String confirmSMS(@Valid @ModelAttribute(name = "phoneAuthForm") PhoneAuthForm phoneAuthForm,
                             BindingResult bindingResult,
                             Model model) {
        String email = smsService.verifySMS(phoneAuthForm, bindingResult, "inputCode");

        if (bindingResult.hasErrors()) {
            return "FindEmail";
        }

        model.addAttribute("email", email);
        return "ShowFindEmailForm";
    }
}
