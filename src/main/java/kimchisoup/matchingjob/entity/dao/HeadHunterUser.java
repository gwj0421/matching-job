package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HEAD_HUNTER_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HeadHunterUser extends SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "headHunterUser")
    private List<Proposal> proposals = new ArrayList<>();

    @Builder
    public HeadHunterUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl) {
        super(name, email, password, phoneNumber, nickName, profileImageUrl);
    }
}
