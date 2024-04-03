package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.InterestField;
import kimchisoup.matchingjob.entity.common.KoreanRegion;
import kimchisoup.matchingjob.utils.converter.EnumConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOB_SEEKER_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobSeekerUser extends SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String githubToken;
    @Enumerated(EnumType.STRING)
    private KoreanRegion residence;
    @Convert(converter = EnumConverter.class)
    private List<KoreanRegion> preferredRegion;
    @Convert(converter = EnumConverter.class)
    private List<InterestField> interestFields;

    @ManyToMany
    @JoinTable(
            name = "job_offers",
            joinColumns = @JoinColumn(name = "job_seeker_user_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id")
    )
    private List<Proposal> offers = new ArrayList<>();

    @OneToMany(mappedBy = "jobSeekerUser")
    private List<Resume> resumes = new ArrayList<>();

    @Builder
    public JobSeekerUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl, String githubToken, KoreanRegion residence, List<KoreanRegion> preferredRegion, List<InterestField> interestFields) {
        super(name, email, password, phoneNumber, nickName, profileImageUrl);
        this.githubToken = githubToken;
        this.residence = residence;
        this.preferredRegion = preferredRegion;
        this.interestFields = interestFields;
    }
}
