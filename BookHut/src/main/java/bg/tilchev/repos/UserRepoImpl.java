package bg.tilchev.repos;

import bg.tilchev.model.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017-02-18.
 */
public class UserRepoImpl implements UserRepo {

    private static UserRepo repo;

    private List<User> users;

    private UserRepoImpl() {
        super();
        this.users = new ArrayList<>();
    }

    public static UserRepo getInstance() {
        if(repo == null) {
            repo = new UserRepoImpl();
        }
        return repo;
    }

    @Override
    public void createUser(User user) {
        this.users.add(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new IllegalArgumentException("No such User found");
    }
}
