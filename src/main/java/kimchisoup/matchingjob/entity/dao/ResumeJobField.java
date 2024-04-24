package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_SEEKER_USER_INTEREST_FIELD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResumeJobField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    @ManyToOne
    @JoinColumn(name = "job_field_id")
    private JobField jobField;

    @Builder
    public ResumeJobField(Resume resume, JobField jobField) {
        this.resume = resume;
        this.jobField = jobField;
    }
}
