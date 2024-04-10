package kimchisoup.matchingjob.entity.dto;



import lombok.Getter;

@Getter
public class PreInterviewAnswerForm {
    private Long id;
    private String answer;
    private Long questionId;
    private Long jobSeekerId;

}
