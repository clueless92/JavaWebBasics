package bg.tilchev.service;

import bg.tilchev.model.dto.binding.LoginUser;
import bg.tilchev.model.entity.User;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.repos.UserRepoImpl;
import org.modelmapper.ModelMapper;

/**
 * Created on 2017-02-18.
 */
public class UserServiceImpl implements UserService {

    private UserRepo repo;
    private ModelMapper mapper;

    public UserServiceImpl() {
        this.repo = UserRepoImpl.getInstance();
        this.mapper = new ModelMapper();
    }

    @Override
    public void createUser(LoginUser loginUser) {
        User user = this.mapper.map(loginUser, User.class);
        this.repo.createUser(user);
    }

    @Override
    public LoginUser findByUsernameAndPassword(String username, String password) {
        User user = this.repo.findByUsernameAndPassword(username, password);
        return this.mapper.map(user, LoginUser.class);
    }
}
