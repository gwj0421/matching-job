package kimchisoup.matchingjob.entity.dto;

import lombok.Getter;

@Getter
public class SignUpForHeadHunterForm extends SingUpUserForm {
    public SignUpForHeadHunterForm(String name, String email, String password1, String password2, String phoneNumber, String nickName) {
        super(name, email, password1, password2, phoneNumber, nickName);
    }
}
