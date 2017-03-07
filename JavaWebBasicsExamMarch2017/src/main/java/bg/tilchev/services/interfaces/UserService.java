package bg.tilchev.services.interfaces;

import bg.tilchev.models.binding.UserLogin;
import bg.tilchev.models.binding.UserRegister;
import bg.tilchev.models.view.GameDisplay;
import bg.tilchev.models.view.UserCurrent;

import java.util.Set;

/**
 * Created on 2017-03-02.
 */
public interface UserService {

    void registerUser(UserRegister userRegister);

    UserCurrent getCurrentUser(UserLogin userLogin);

    boolean exists(String username);

    void buyGames(UserCurrent userCurrent, Set<GameDisplay> gameDisplaySet);
}
