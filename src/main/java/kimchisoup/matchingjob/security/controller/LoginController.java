package kimchisoup.matchingjob.security.controller;

import jakarta.validation.Valid;
import kimchisoup.matchingjob.entity.dao.SiteUser;
import kimchisoup.matchingjob.error.ErrorMessage;
import kimchisoup.matchingjob.security.entity.*;
import kimchisoup.matchingjob.security.service.EmailService;
import kimchisoup.matchingjob.security.service.SMSService;
import kimchisoup.matchingjob.service.SiteUserService;
import kimchisoup.matchingjob.utils.RandomUtils;
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
    private final SiteUserService siteUserService;
    private final SMSService smsService;
    private final EmailService emailService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(@RequestParam(value = "errorCode", required = false) Integer errorCode,
                        Model model) {
        model.addAttribute("errorCode", errorCode);
        return "LoginForm";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/jobSeeker/signUp")
    public String signUpForJobSeeker(Model model) {
        model.addAttribute("signUpForm", new SignUpForJobSeekerForm());
        return "SignUpForJobSeekerForm";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/jobSeeker/signUp")
    public String signUpForJobSeeker(@Valid @ModelAttribute(name = "signUpForm") SignUpForJobSeekerForm signUpForm,
                                     BindingResult bindingResult,
                                     Model model) {
        siteUserService.checkCertification(signUpForm.isCertified(), bindingResult, "certified");
        siteUserService.checkMatchPassword(signUpForm.getPassword1(), signUpForm.getPassword2(), bindingResult, "password2");
        siteUserService.checkExistEmail(signUpForm.getEmail(), bindingResult, "email");

        if (bindingResult.hasErrors()) {
            return "SignUpForJobSeekerForm";
        }
        siteUserService.saveUserForJobSeeker(signUpForm);

        return "redirect:/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/headHunter/signUp")
    public String signUpForHeadHunter(Model model) {
        model.addAttribute("signUpForm", new SignUpForHeadHunterForm());
        return "SignUpForHeadHunterForm";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/headHunter/signUp")
    public String signUpForHeadHunter(@Valid @ModelAttribute(name = "signUpForm") SignUpForHeadHunterForm signUpForm,
                                      BindingResult bindingResult,
                                      Model model) {
        siteUserService.checkCertification(signUpForm.isCertified(), bindingResult, "certified");
        siteUserService.checkMatchPassword(signUpForm.getPassword1(), signUpForm.getPassword2(), bindingResult, "password2");
        siteUserService.checkExistEmail(signUpForm.getEmail(), bindingResult, "email");

        if (bindingResult.hasErrors()) {
            return "SignUpForHeadHunterForm";
        }
        siteUserService.saveUserForHeadHunter(signUpForm);

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
        siteUserService.checkMatchPassword(changePasswordForm.getNewPassword(), changePasswordForm.getNewPasswordForCheck(), bindingResult, "newPasswordForCheck");
        Optional<SiteUser> findUser = siteUserService.checkPasswordByEmail(changePasswordForm.getPassword(), user.getUsername(), bindingResult, "password");

        if (bindingResult.hasErrors()) {
            return "ChangePasswordForm";
        }

        siteUserService.changePassword(findUser, changePasswordForm.getNewPassword());
        return "redirect:/";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/resetPassword")
    public String resetPasswordByEmail(Model model, @RequestParam(name = "isSuccess", required = false, defaultValue = "false") boolean isSuccess) {
        model.addAttribute("isSuccess", isSuccess);
        model.addAttribute("emailAuthForm", new EmailAuthForm());
        return "ResetPasswordForm";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/resetPassword")
    public String resetPasswordByEmail(@Valid @ModelAttribute(name = "emailAuthForm") EmailAuthForm emailAuthForm,
                                       BindingResult bindingResult,
                                       Model model) {
        ResponseEntity response = emailService.verifyEmail(emailAuthForm, bindingResult);
        if (!response.getStatusCode().is2xxSuccessful()) {
            if (response.getBody().equals(ErrorMessage.WRONG_EMAIL_OR_CERTIFICATION_CODE)) {
                bindingResult.rejectValue("certificationCode", "error.certificationCode", ErrorMessage.WRONG_EMAIL_OR_CERTIFICATION_CODE.getDesc());
            }
            return "ResetPasswordForm";
        }
        Optional<SiteUser> user = siteUserService.findUserByEmail(emailAuthForm.getEmail());
        if (user.isEmpty()) {
            bindingResult.rejectValue("email", "error.email", ErrorMessage.WRONG_SITE_USER_BY_EMAIL.getDesc());
            return "ResetPasswordForm";
        }
        String initPassword = RandomUtils.createInitPassword();
        siteUserService.changePassword(user, initPassword);
        emailService.sendInitPassword(emailAuthForm.getEmail(), initPassword);
        return "redirect:/resetPassword?isSuccess=true";
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/findEmail")
    public String sendSMS(Model model) {
        model.addAttribute("phoneAuthForm", new PhoneAuthForm());
        return "FindEmail";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/findEmail/confirmByPhoneNumber")
    public String confirmSMS(@Valid @ModelAttribute(name = "phoneAuthForm") PhoneAuthForm phoneAuthForm,
                             BindingResult bindingResult,
                             Model model) {
        ResponseEntity response = smsService.verifySMS(phoneAuthForm, bindingResult);
        if (!response.getStatusCode().is2xxSuccessful()) {
            if (response.getBody().equals(ErrorMessage.WRONG_PHONE_NUMBER_OR_CERTIFICATION_CODE)) {
                bindingResult.rejectValue("certificationCode", "error.certificationCode", ErrorMessage.WRONG_PHONE_NUMBER_OR_CERTIFICATION_CODE.getDesc());
            } else if (response.getBody().equals(ErrorMessage.WRONG_SITE_USER_BY_PHONE_NUMBER)) {
                bindingResult.rejectValue("phoneNumber", "error.phoneNumber", ErrorMessage.WRONG_SITE_USER_BY_PHONE_NUMBER.getDesc());
            }
            return "FindEmail";
        }
        Optional<SiteUser> user = siteUserService.findUserByPhoneNumber(phoneAuthForm.getPhoneNumber());
        if (user.isEmpty()) {
            bindingResult.rejectValue("phoneNumber", "error.phoneNumber", ErrorMessage.WRONG_SITE_USER_BY_PHONE_NUMBER.getDesc());
            return "FindEmail";
        } else {
            model.addAttribute("email", user.get().getEmail());
        }
        return "ShowFindEmailForm";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sms-certification/send")
    @ResponseBody
    public ResponseEntity sendSMSResponse(String phoneNumber) {
        return smsService.sendSMSResponse(phoneNumber);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/email-certification/send")
    @ResponseBody
    public ResponseEntity sendEmailResponse(String email) {
        return emailService.sendEmail(email);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sms-certification/confirm")
    @ResponseBody
    public ResponseEntity confirmSMSResponse(@Valid @ModelAttribute(name = "phoneAuthForm") PhoneAuthForm phoneAuthForm,
                                             BindingResult bindingResult) {
        return smsService.verifySMS(phoneAuthForm, bindingResult);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/email-certification/confirm")
    @ResponseBody
    public ResponseEntity confirmEmailResponse(@Valid @ModelAttribute(name = "emailAuthForm") EmailAuthForm emailAuthForm,
                                               BindingResult bindingResult) {
        return emailService.verifyEmail(emailAuthForm, bindingResult);
    }
}
