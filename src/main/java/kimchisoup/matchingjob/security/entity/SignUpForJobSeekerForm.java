package kimchisoup.matchingjob.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import kimchisoup.matchingjob.entity.common.RegionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SignUpForJobSeekerForm extends SignUpUserForm{
    private String githubToken;
    private RegionType residence;

    public SignUpForJobSeekerForm() {
    }

    @JsonCreator
    public SignUpForJobSeekerForm(String name, String email, String password1, String password2, String phoneNumber, String nickName, MultipartFile profileImage, String githubToken, RegionType residence) {
        super(name, email, password1, password2, phoneNumber, nickName, profileImage);
        this.githubToken = githubToken;
        this.residence = residence;
    }
}
