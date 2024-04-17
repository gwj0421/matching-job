package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.job.JobCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOB_FIELD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private JobCategory jobCategory;
    private Enum subJobCategory;

    @OneToMany(mappedBy = "jobField")
    private List<JobSeekerUserJobField> jobSeekerUserJobFields = new ArrayList<>();

    public JobField(JobCategory jobCategory, int subJobCategoryIdx) {
        this.jobCategory = jobCategory;
        this.subJobCategory = jobCategory.getSubCategory()[subJobCategoryIdx];
    }

    public void addJobSeekerJobField(JobSeekerUserJobField jobSeekerUserJobField) {
        this.jobSeekerUserJobFields.add(jobSeekerUserJobField);
    }

    public void removeJobSeekerJobField(JobSeekerUserJobField jobSeekerUserJobField) {
        this.jobSeekerUserJobFields.remove(jobSeekerUserJobField);
    }
}
