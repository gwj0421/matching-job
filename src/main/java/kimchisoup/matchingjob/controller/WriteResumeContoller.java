package kimchisoup.matchingjob.controller;

import kimchisoup.matchingjob.entity.common.CareerExperience;
import kimchisoup.matchingjob.entity.dao.Resume;
import kimchisoup.matchingjob.entity.dto.WriteResumeForm;
import kimchisoup.matchingjob.service.WriteResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequiredArgsConstructor
public class WriteResumeContoller {

    private final WriteResumeService writeResumeService;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/resume")
    public String resumeForm() {
        return "resume";
    }

    @PostMapping(value = "/submitted")
    public String create(@RequestBody WriteResumeForm writeResumeForm, Model model) {
        WriteResumeForm user = new WriteResumeForm("", CareerExperience.FRESH_MAN, "", "","",0,"alswl@naver.com","","","","");
        model.addAttribute("user", user);
        writeResumeService.create(writeResumeForm);
        return "redirect:/";
    }
}