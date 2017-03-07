package bg.tilchev.services.interfaces;

import bg.tilchev.models.binding.UserLogin;
import bg.tilchev.models.binding.UserRegister;
import bg.tilchev.models.entities.User;
import bg.tilchev.models.view.UserCurrent;

/**
 * Created on 2017-03-02.
 */
public interface UserService {

    void registerUser(UserRegister userRegister);

    UserCurrent getCurrentUser(UserLogin userLogin);

    boolean exists(String username);
}
