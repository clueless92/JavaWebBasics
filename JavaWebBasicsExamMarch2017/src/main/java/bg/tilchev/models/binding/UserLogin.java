package bg.tilchev.models.binding;

import java.io.Serializable;

/**
 * Created on 2017-03-02.
 */
public class UserLogin implements Serializable {

    private String email;

    private String password;

    public UserLogin() {
        super();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
