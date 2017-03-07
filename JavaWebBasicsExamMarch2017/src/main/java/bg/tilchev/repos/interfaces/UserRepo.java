package bg.tilchev.repos.interfaces;

import bg.tilchev.models.entities.Game;
import bg.tilchev.models.entities.User;

import java.util.Set;

/**
 * Created on 2017-03-02.
 */
public interface UserRepo {

    void persist(User user);

    User findById(Long id);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    Set<User> findAll();

    void update(User user);

    Set<Game> getGamesOfUser(Long id);
}
