package kimchisoup.matchingjob.entity.dto;

import kimchisoup.matchingjob.entity.common.InterestField;
import kimchisoup.matchingjob.entity.common.KoreanRegion;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class SignUpForJobSeekerForm extends SingUpUserForm {
    private String githubToken;
    private KoreanRegion residence;
    private List<KoreanRegion> preferredRegion;
    private List<InterestField> interestFields;

    public SignUpForJobSeekerForm(String name, String email, String password1, String password2, String phoneNumber, String nickName, MultipartFile profileImage, String githubToken, KoreanRegion residence, List<KoreanRegion> preferredRegion, List<InterestField> interestFields) {
        super(name, email, password1, password2, phoneNumber, nickName, profileImage);
        this.githubToken = githubToken;
        this.residence = residence;
        this.preferredRegion = preferredRegion;
        this.interestFields = interestFields;
    }
}
