package kimchisoup.matchingjob.controller;

import jakarta.validation.Valid;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String resumeForm(Model model) {
        model.addAttribute("resume", new ResumeForm());
        return "ResumeForm";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String create(@AuthenticationPrincipal CustomUserDetails user,
                         @Valid @ModelAttribute(name = "resume") ResumeForm resumeForm,
                         BindingResult bindingResult,
                         Model model) {
        resumeService.createResume(user, resumeForm);
        if (bindingResult.hasErrors()) {
            return "ResumeForm";
        }
        return "redirect:/";
    }
}
