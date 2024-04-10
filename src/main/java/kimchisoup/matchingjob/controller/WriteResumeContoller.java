package kimchisoup.matchingjob.controller;

import kimchisoup.matchingjob.entity.dto.WritingResumeForm;
import kimchisoup.matchingjob.service.WriteResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WriteResumeContoller {
    private final WriteResumeService writeResumeService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/resume")
    public String resumeForm(Model model) {
        model.addAttribute("resume", new WritingResumeForm());
        return "resume";
    }

    @PostMapping(value = "/submitted")
    public String create(@ModelAttribute("resume") WritingResumeForm writingResumeForm) {
        writeResumeService.create(writingResumeForm);
        return "redirect:/";
    }
}