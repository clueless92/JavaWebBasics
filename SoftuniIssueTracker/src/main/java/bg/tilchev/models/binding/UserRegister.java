package bg.tilchev.models.binding;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on 2017-03-02.
 */
public class UserRegister implements Serializable {

    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 symbols")
    private String username;

    @Size(min = 5, message = "Full Name must be at least 5 symbols")
    private String fullName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*,.])[A-Za-z\\d!@#$%^&*,.]{8,}$",
    message = "Password must be at least 8 symbols. It should contain a capital letter, a number and one of the " +
            "following signs: [!@#$%^&*,.]")
    private String password;

    private String confirmPassword;

    public UserRegister() {
        super();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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
