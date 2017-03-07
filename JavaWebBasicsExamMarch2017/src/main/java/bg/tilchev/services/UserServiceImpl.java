package bg.tilchev.services;

import bg.tilchev.models.binding.UserLogin;
import bg.tilchev.models.binding.UserRegister;
import bg.tilchev.models.entities.Game;
import bg.tilchev.models.entities.User;
import bg.tilchev.models.enums.Role;
import bg.tilchev.models.view.GameDisplay;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.repos.interfaces.GameRepo;
import bg.tilchev.repos.interfaces.UserRepo;
import bg.tilchev.services.interfaces.UserService;
import bg.tilchev.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created on 2017-03-02.
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepo userRepo;

    @Inject
    private ModelParser modelParser;

    @Inject
    private GameRepo gameRepo;

    public UserServiceImpl() {
        super();
    }

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
        User user = this.userRepo.findByEmail(userLogin.getEmail());
        if (null == user) {
            return null;
        }
        UserCurrent userCurrent = this.modelParser.convert(user, UserCurrent.class);
        Set<Game> games = user.getGames();
        for (Game game : games) {
            userCurrent.addGameId(game.getId());
        }
        return userCurrent;
    }

    @Override
    public boolean exists(String username) {
        return null != this.userRepo.findByEmail(username);
    }

    @Override
    public void buyGames(UserCurrent userCurrent, Set<GameDisplay> gameDisplaySet) {
        User user = this.userRepo.findByEmail(userCurrent.getEmail());
        Set<Game> games = user.getGames();
        for (GameDisplay gameDisplay : gameDisplaySet) {
            Game game = this.gameRepo.findById(gameDisplay.getId());
            game.getOwners().add(user);
            this.gameRepo.update(game);
            games.add(game);
            userCurrent.addGameId(game.getId());
        }
        user.setGames(games);
        this.userRepo.update(user);
    }
}
