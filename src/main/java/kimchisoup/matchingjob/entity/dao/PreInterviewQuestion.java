package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRE_INTERVIEW_QUESTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PreInterviewQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "headhunter_id")
    private HeadHunterUser headHunter;

    @ManyToOne
    @JoinColumn(name = "pre_interview_list_id")
    private PreInterviewList preInterviewList;

    @Builder
    public PreInterviewQuestion(Long id, String questionText, HeadHunterUser headHunter, PreInterviewList preInterviewList) {
        this.id = id;
        this.questionText = questionText;
        this.headHunter = headHunter;
        this.preInterviewList = preInterviewList;
    }


}
