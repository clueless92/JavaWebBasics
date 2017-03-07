package bg.tilchev.services;

import bg.tilchev.models.binding.UserLogin;
import bg.tilchev.models.binding.UserRegister;
import bg.tilchev.models.entities.User;
import bg.tilchev.models.enums.Role;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.repos.interfaces.UserRepo;
import bg.tilchev.services.interfaces.UserService;
import bg.tilchev.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created on 2017-03-02.
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepo userRepo;

    @Inject
    private ModelParser modelParser;

    @Override
    public void registerUser(UserRegister userRegister) {
        User user = this.modelParser.convert(userRegister, User.class);
        user.setRole(Role.USER);
        if (this.userRepo.findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
        }
        this.userRepo.persist(user);
    }

    @Override
    public UserCurrent getCurrentUser(UserLogin userLogin) {
        User user = this.userRepo.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        if (null == user) {
            return null;
        }
        UserCurrent userCurrent = this.modelParser.convert(user, UserCurrent.class);
        return userCurrent;
    }

    @Override
    public boolean exists(String username) {
        return null != this.userRepo.findByUsername(username);
    }
}
