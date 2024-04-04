package kimchisoup.matchingjob.entity.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import kimchisoup.matchingjob.entity.common.InterestFieldType;
import kimchisoup.matchingjob.entity.common.RegionType;
import lombok.Getter;

import java.util.List;

@Getter
public class SignUpForJobSeekerForm extends SingUpUserForm{
    private String githubToken;
    private RegionType residence;
    private List<RegionType> preferredRegion;
    private List<InterestFieldType> interestFieldTypes;

    @JsonCreator
    public SignUpForJobSeekerForm(String name, String email, String password1, String password2, String phoneNumber, String nickName, String githubToken, RegionType residence, List<RegionType> preferredRegion, List<InterestFieldType> interestFieldTypes) {
        super(name, email, password1, password2, phoneNumber, nickName);
        this.githubToken = githubToken;
        this.residence = residence;
        this.preferredRegion = preferredRegion;
        this.interestFieldTypes = interestFieldTypes;
    }
}
