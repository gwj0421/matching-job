package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.security.entity.Authority;
import kimchisoup.matchingjob.entity.common.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@Entity
@Table(name = "SITE_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok을 사용하여 생성자 생성
@Getter
public abstract class SiteUser extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickName;
    private URL profileImageUrl;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public SiteUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl, Authority authority) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.authority = authority;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
