package bg.tilchev.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on 2017-03-02.
 */
public class UserRegister implements Serializable {

    @Pattern(regexp = "^[^@]+@[^@]+\\.[^@]+$",
    message = "Email must contain @ sign and a period.")
    private String email;

    @NotNull(message = "Full name must not be empty.")
    private String fullName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$",
    message = "Password length must be at least 6 symbols and must contain at least 1 uppercase, 1 lowercase " +
            "letter and 1 digit.")
    private String password;

    private String confirmPassword;

    public UserRegister() {
        super();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
