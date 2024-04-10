package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PRE_INTERVIEW_ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PreInterviewAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "jobSeekerUser_id")
    private JobSeekerUser jobSeekerUser;

    @ManyToOne
    @JoinColumn(name = "pre_interview_question_id")
    private PreInterviewQuestion preInterviewQuestion;

    @Builder
    public PreInterviewAnswer(PreInterviewQuestion preInterviewQuestion, JobSeekerUser jobSeekerUsers, String answer) {
        this.preInterviewQuestion = preInterviewQuestion;
        this.jobSeekerUser = jobSeekerUsers;
        this.answer = answer;
    }
}
