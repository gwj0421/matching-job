package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PRE_INTERVIEW_LIST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PreInterviewAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer")
    private String answer;

    @OneToMany
    @JoinColumn(name = "jobSeekerUser_id")
    private List<JobSeekerUser> jobSeekerUsers;

    @OneToOne(mappedBy = "preInterviewAnswer")
    private PreInterviewQuestion preInterviewQuestion;

    @Builder
    public PreInterviewAnswer(PreInterviewQuestion preInterviewQuestion, List<JobSeekerUser> jobSeekerUsers, String answer) {
        this.preInterviewQuestion = preInterviewQuestion;
        this.jobSeekerUsers = jobSeekerUsers;
        this.answer = answer;
    }
}
