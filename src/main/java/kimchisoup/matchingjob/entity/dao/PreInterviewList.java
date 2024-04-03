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
public class PreInterviewList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer")
    private String answer;

    @OneToMany(mappedBy = "preInterviewList")
    private List<PreInterviewQuestion> preInterviewQuestions;

    @Builder
    public PreInterviewList(Long id, List<PreInterviewQuestion> preInterviewQuestions) {
        this.id = id;
        this.preInterviewQuestions = preInterviewQuestions;
    }
}
