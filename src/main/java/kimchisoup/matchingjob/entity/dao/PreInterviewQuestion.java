package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "pre_interview_list_id")
    private List<PreInterviewAnswer> preInterviewAnswers;

    @Builder
    public PreInterviewQuestion(String questionText, HeadHunterUser headHunter, List<PreInterviewAnswer> preInterviewAnswers) {
        this.questionText = questionText;
        this.headHunter = headHunter;
        this.preInterviewAnswers = preInterviewAnswers;
    }


}
