package kimchisoup.matchingjob.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SignUpUserForm {
    @NotBlank
    @Size(min = 2,max = 10)
    private String name;
    @Email
    private String email;
    @NotBlank
    @Size(min = 8,max = 15)
    private String password1;
    @NotBlank
    @Size(min = 8,max = 15)
    private String password2;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Size(min = 2,max = 10)
    private String nickName;
    private MultipartFile profileImage;
    public SignUpUserForm(String name, String email, String password1, String password2, String phoneNumber, String nickName) {}
    public SignUpUserForm() {}

    @JsonCreator
    public SignUpUserForm(String name, String email, String password1, String password2, String phoneNumber, String nickName, MultipartFile profileImage) {
        this.name = name;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }
}