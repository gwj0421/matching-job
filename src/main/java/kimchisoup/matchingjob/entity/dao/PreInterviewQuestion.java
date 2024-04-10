package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @JoinColumn(name = "head_hunter_user_id")
    private HeadHunterUser headHunterUser;

    @OneToMany
   // @JoinColumn(name = "preInterviewQuestion")
    private List<PreInterviewAnswer> preInterviewAnswers = new ArrayList<>();

    @Builder
    public PreInterviewQuestion(String questionText, HeadHunterUser headHunterUser) {
        this.questionText = questionText;
        this.headHunterUser = headHunterUser;
    }


    public void addPreInterviewAnswer(PreInterviewAnswer preInterviewAnswer) { this.preInterviewAnswers.add(preInterviewAnswer); }

    public void addHeadHunterUSser(HeadHunterUser headHunterUser) {}

}
