package kimchisoup.matchingjob.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordForm {
    @NotEmpty
    private String password;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String newPasswordForCheck;

    public ChangePasswordForm() {
    }

    @JsonCreator
    public ChangePasswordForm(String password, String newPassword, String newPasswordForCheck) {
        this.password = password;
        this.newPassword = newPassword;
        this.newPasswordForCheck = newPasswordForCheck;
    }
}
