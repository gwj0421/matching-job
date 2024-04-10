package kimchisoup.matchingjob.security.entity;

import lombok.Getter;

import java.net.URL;

@Getter
public class ChangeUserForm {
    private final String name;
    private final String phoneNumber;
    private final String nickName;
    private final URL profileImageUrl;

    public ChangeUserForm(String name, String phoneNumber, String nickName, URL profileImageUrl) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }
}
