package bg.tilchev.service;

import bg.tilchev.model.dto.binding.LoginUser;

/**
 * Created on 2017-02-18.
 */
public interface UserService {

    void createUser(LoginUser loginModel);

    LoginUser findByUsernameAndPassword(String username, String password);
}
