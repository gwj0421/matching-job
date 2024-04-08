package kimchisoup.matchingjob.controller;

import kimchisoup.matchingjob.entity.dto.WriteResumeForm;
import kimchisoup.matchingjob.service.WriteResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static kimchisoup.matchingjob.entity.common.CareerExperience.FRESH_MAN;

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
        WriteResumeForm writeResumeForm = new WriteResumeForm("", FRESH_MAN,"","","",24,"","","","","");
        model.addAttribute("resume", writeResumeForm);
        return "resume";
    }

    @PostMapping(value = "/submitted")
    public String create(@ModelAttribute("resume") WriteResumeForm writeResumeForm) {
        writeResumeService.create(writeResumeForm);
        return "redirect:/";
    }
}