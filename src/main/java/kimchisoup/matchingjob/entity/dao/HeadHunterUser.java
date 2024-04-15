package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.security.entity.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HEAD_HUNTER_USER")
@DiscriminatorValue("HeadHunterUser")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HeadHunterUser extends SiteUser {
    @OneToMany(mappedBy = "headHunterUser")
    private List<Proposal> proposals = new ArrayList<>();

    @Builder
    public HeadHunterUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl, Authority authority) {
        super(name, email, password, phoneNumber, nickName, profileImageUrl, authority);
    }

    public void addProposal(Proposal proposal) {
        this.proposals.add(proposal);
    }

    public void removeProposal(Proposal proposal) {
        this.proposals.remove(proposal);
    }
}
