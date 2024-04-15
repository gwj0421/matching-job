package kimchisoup.matchingjob.controller;

import jakarta.validation.Valid;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.ResumeForm;
import kimchisoup.matchingjob.security.entity.CustomUserDetails;
import kimchisoup.matchingjob.service.ResumeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    @GetMapping
    public String resumeForm(Model model) {
        model.addAttribute("resume", new ResumeForm());
        return "ResumeForm";
    }

    @GetMapping("/list")
    public String resumeList(Model model){
        List<Resume> resumes = resumeService.getData();
        model.addAttribute("ResumeList", resumes);
        return "ResumeList";
    }

//    @PostMapping
//    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
//    public String create(@AuthenticationPrincipal CustomUserDetails user,
//                         @Valid @ModelAttribute(name = "resume") ResumeForm resumeForm,
//                         BindingResult bindingResult,
//                         Model model) {
//        resumeService.createResume(user, resumeForm);
//        if (bindingResult.hasErrors()) {
//            return "ResumeForm";
//        }
//        return "redirect:/";
//    @PostMapping
//    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
//    public String create(@AuthenticationPrincipal CustomUserDetails user,
//                                         @Valid @ModelAttribute(name = "resume") ResumeForm resumeForm,
//                                         BindingResult bindingResult,
//                                         Model model) {
//        resumeService.createResume(user, resumeForm);
//        if (bindingResult.hasErrors()) {
//            return "ResumeForm";
//        }
//        return "ResumeList";
//    }

    @PostMapping
    public String create(@Valid @ModelAttribute(name = "resume") ResumeForm resumeForm,
                                         BindingResult bindingResult,
                                         Model model) {
        resumeService.createResume(resumeForm);
        if (bindingResult.hasErrors()) {
            return "ResumeForm";
        }
        return "redirect:/resume/list";
    }
//    @DeleteMapping("delete/id")
//    @PreAuthorize("hasAnyRole('ROLE_JOB_SEEKER','ROLE_ADMIN')")
    @DeleteMapping("/delete/{resumeId}")
    public String delete(@PathVariable long resumeId){
        resumeService.deleteResume(resumeId);
        return "redirect:/resume/list";
    }
}