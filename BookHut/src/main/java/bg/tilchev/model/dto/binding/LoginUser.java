package bg.tilchev.model.dto.binding;

import java.io.Serializable;

/**
 * Created on 2017-02-18.
 */
public class LoginUser implements Serializable {

    private String username;
    private String password;

    public LoginUser() {
        super();
    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
