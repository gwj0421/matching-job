package kimchisoup.matchingjob.entity.dao;

import kimchisoup.matchingjob.entity.common.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok을 사용하여 생성자 생성
@Getter
public class SiteUser extends BaseTime {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickName;
    private URL profileImageUrl;

    public SiteUser(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
