package softuni.repos;

import softuni.models.User;

/**
 * Created on 2017-02-26.
 */
public interface UserRepo {

    void persist(User user);

    User retrieve(Long id);

    User retrieve(String email);

    boolean exists(String email);
}
