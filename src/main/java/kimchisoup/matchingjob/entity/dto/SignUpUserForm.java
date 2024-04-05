package kimchisoup.matchingjob.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SingUpUserForm {
    @NotBlank
    @Min(2)
    @Max(10)
    private String name;
    @Email
    private String email;
    @NotBlank
    @Min(8)
    @Max(15)
    private String password1;
    @NotBlank
    @Min(8)
    @Max(15)
    private String password2;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Min(1)
    @Max(10)
    private String nickName;

    public SingUpUserForm(String name, String email, String password1, String password2, String phoneNumber, String nickName) {
        this.name = name;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
    }
}
