package bg.tilchev.repos;

import bg.tilchev.model.entity.User;

/**
 * Created on 2017-02-18.
 */
public interface UserRepo {

    void createUser(User user);

    User findByUsernameAndPassword(String username, String password);
}
