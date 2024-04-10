package kimchisoup.matchingjob.entity.dto;

import lombok.Getter;

@Getter
public class PreInterviewQuestionForm {
    private Long id;
    private String question;
    private Long headhunterId;
}
