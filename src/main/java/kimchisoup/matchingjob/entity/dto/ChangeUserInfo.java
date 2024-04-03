package kimchisoup.matchingjob.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.net.URL;

    @Getter
    public class ChangeUserInfo {
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String nickName;
        private URL profileImageUrl;

        @Builder
    public ChangeUserInfo(String name, String email, String password, String phoneNumber, String nickName, URL profileImageUrl) {
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
