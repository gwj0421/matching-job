package kimchisoup.matchingjob.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneAuthForm {
    @NotBlank
    @Size(min = 9,max = 11)
    private String phoneNumber;
    @NotBlank
    private String inputCode;

    public PhoneAuthForm() {
    }

    @JsonCreator
    public PhoneAuthForm(String phoneNumber, String inputCode) {
        this.phoneNumber = phoneNumber;
        this.inputCode = inputCode;
    }
}
