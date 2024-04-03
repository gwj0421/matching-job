package kimchisoup.matchingjob.entity.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SignUpForHeadHunterForm extends SingUpUserForm {
    public SignUpForHeadHunterForm(String name, String email, String password1, String password2, String phoneNumber, String nickName, MultipartFile profileImage) {
        super(name, email, password1, password2, phoneNumber, nickName, profileImage);
    }
}
