package bg.tilchev.repos.interfaces;

import bg.tilchev.models.entities.User;

import java.util.Set;

/**
 * Created on 2017-03-02.
 */
public interface UserRepo {

    void persist(User user);

    User findById(Long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    Set<User> findAll();
}
