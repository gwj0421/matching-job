package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.RegionType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RegionType regionType;

    @OneToMany(mappedBy = "region")
    private List<JobSeekerUserRegion> jobSeekerUserRegions = new ArrayList<>();

    @Builder
    public Region(RegionType regionType) {
        this.regionType = regionType;
    }

    public void changeRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public void addJobSeekerUserRegion(JobSeekerUserRegion jobSeekerUserRegion) {
        this.jobSeekerUserRegions.add(jobSeekerUserRegion);
    }

    public void removeJobSeekerUserRegion(JobSeekerUserRegion jobSeekerUserRegion) {
        this.jobSeekerUserRegions.remove(jobSeekerUserRegion);
    }
}
