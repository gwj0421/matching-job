package kimchisoup.matchingjob.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAuthForm {
    @NotBlank
    @Size(min = 3)
    private String email;
    @NotBlank
    private String certificationCode;

    public EmailAuthForm() {
    }

    @JsonCreator
    public EmailAuthForm(String email, String certificationCode) {
        this.email = email;
        this.certificationCode = certificationCode;
    }
}
