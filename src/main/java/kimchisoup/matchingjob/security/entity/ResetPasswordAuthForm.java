package kimchisoup.matchingjob.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ResetPasswordAuthForm {
    @NotBlank
    @Size(min = 9,max = 11)
    private String phoneNumber;
    @NotBlank
    private String authCode;

    public ResetPasswordAuthForm() {
    }

    @JsonCreator
    public ResetPasswordAuthForm(String phoneNumber, String authCode) {
        this.phoneNumber = phoneNumber;
        this.authCode = authCode;
    }
}
