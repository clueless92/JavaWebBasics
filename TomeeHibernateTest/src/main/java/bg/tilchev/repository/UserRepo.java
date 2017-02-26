package bg.tilchev.repository;

import bg.tilchev.entities.User;

public interface UserRepo {

    void create(User user);

    User getByUsername(String username);
}
