package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.InterestFieldType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INTEREST_FIELD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InterestField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private InterestFieldType interestFieldType;

    @OneToMany(mappedBy = "interestField")
    private List<JobSeekerUserInterestField> jobSeekerUserInterestFields = new ArrayList<>();

    public InterestField(InterestFieldType interestFieldType) {
        this.interestFieldType = interestFieldType;
    }

    public void addJobSeekerUserInterestField(JobSeekerUserInterestField jobSeekerUserInterestField) {
        jobSeekerUserInterestFields.add(jobSeekerUserInterestField);
    }
}
