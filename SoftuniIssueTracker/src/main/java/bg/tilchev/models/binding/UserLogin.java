package bg.tilchev.models.binding;

import java.io.Serializable;

/**
 * Created on 2017-03-02.
 */
public class UserLogin implements Serializable {

    private String username;

    private String password;

    public UserLogin() {
        super();
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
