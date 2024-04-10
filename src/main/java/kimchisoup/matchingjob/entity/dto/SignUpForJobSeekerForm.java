//package kimchisoup.matchingjob.entity.dto;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import kimchisoup.matchingjob.entity.common.job.JobCategory;
//import kimchisoup.matchingjob.entity.common.RegionType;
//import lombok.Getter;
//
//import java.util.List;
//
//@Getter
//public class SignUpForJobSeekerForm extends SingUpUserForm{
//    private String githubToken;
//    private RegionType residence;
//    private List<RegionType> preferredRegion;
//    private List<JobCategory> jobCategories;
//
//    @JsonCreator
//    public SignUpForJobSeekerForm(String name, String email, String password1, String password2, String phoneNumber, String nickName, String githubToken, RegionType residence, List<RegionType> preferredRegion, List<JobCategory> jobCategories) {
//        super(name, email, password1, password2, phoneNumber, nickName);
//        this.githubToken = githubToken;
//        this.residence = residence;
//        this.preferredRegion = preferredRegion;
//        this.jobCategories = jobCategories;
//    }
//}
