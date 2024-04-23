package kimchisoup.matchingjob.controller;

import jakarta.validation.Valid;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String resumeList(Model model, @AuthenticationPrincipal CustomUserDetails user){
        List<Resume> resumes = resumeService.getData(user.getId());
        model.addAttribute("ResumeList", resumes);
        return "ResumeList";
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
        return "redirect:/resume/list";
    }

    @GetMapping("/read/{resumeId}")
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String read(@PathVariable long resumeId, Model model) {
        Resume resume = resumeService.readResume(resumeId).orElseThrow();
        model.addAttribute("resume", resume);
        return "ReadingResume";
    }

    @DeleteMapping("/delete/{resumeId}")
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String delete(@PathVariable long resumeId){
        resumeService.deleteResume(resumeId);
        return "redirect:/resume/list";
    }

    @GetMapping("/update/{resumeId}")
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String resumeUpdateForm(@PathVariable long resumeId, Model model) {
        Resume resume = resumeService.readResume(resumeId).orElseThrow();
        model.addAttribute("resume", resume);
        return "ResumeUpdateForm";
    }

    @PostMapping("/update/{resumeId}")
    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    public String update(@AuthenticationPrincipal CustomUserDetails user,
                         @Valid @ModelAttribute(name = "resumeUpdating") ResumeForm resumeForm,
                         @PathVariable long resumeId,
                         BindingResult bindingResult) {
            System.out.println("update 실행됨");
            resumeService.updateResume(resumeForm, user, resumeId);

            if (bindingResult.hasErrors()) {
            return "ResumeUpdateForm";
        }
        return "redirect:/resume/list";
    }
}