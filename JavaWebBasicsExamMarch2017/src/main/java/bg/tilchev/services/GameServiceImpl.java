package bg.tilchev.services;

import bg.tilchev.models.binding.GameBindingModel;
import bg.tilchev.models.entities.Game;
import bg.tilchev.models.view.GameDisplay;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.repos.interfaces.GameRepo;
import bg.tilchev.repos.interfaces.UserRepo;
import bg.tilchev.services.interfaces.GameService;
import bg.tilchev.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created on 2017-03-05.
 */
@Stateless
public class GameServiceImpl implements GameService {

    @Inject
    private GameRepo gameRepo;

    @Inject
    private UserRepo userRepo;

    @Inject
    private ModelParser modelParser;

    @Override
    public void submit(GameBindingModel gameBindingModel) {
        Game game = new Game();
        game.setDescription(gameBindingModel.getDescription());
        game.setPrice(Double.parseDouble(gameBindingModel.getPrice()));
        game.setSize(Double.parseDouble(gameBindingModel.getSize()));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            game.setReleaseDate(format.parse(gameBindingModel.getReleaseDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        game.setThumbnail(gameBindingModel.getThumbnail());
        game.setTitle(gameBindingModel.getTitle());
        game.setTrailer(gameBindingModel.getTrailer());
        this.gameRepo.persist(game);
    }

    @Override
    public void edit(GameBindingModel gameBindingModel) {
        Game game = new Game();
        game.setId(gameBindingModel.getId());
        game.setDescription(gameBindingModel.getDescription());
        game.setPrice(Double.parseDouble(gameBindingModel.getPrice()));
        game.setSize(Double.parseDouble(gameBindingModel.getSize()));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            game.setReleaseDate(format.parse(gameBindingModel.getReleaseDate()));
        } catch (ParseException e) {
            Date date = this.gameRepo.findById(gameBindingModel.getId()).getReleaseDate();
            game.setReleaseDate(date);
        }
        game.setThumbnail(gameBindingModel.getThumbnail());
        game.setTitle(gameBindingModel.getTitle());
        game.setTrailer(gameBindingModel.getTrailer());
        this.gameRepo.update(game);
    }

    @Override
    public void delete(Long id) {
        this.gameRepo.deleteById(id);
    }

    @Override
    public GameDisplay getGame(Long id) {
        Game game = this.gameRepo.findById(id);
        GameDisplay gameDisplay = this.modelParser.convert(game, GameDisplay.class);
        return gameDisplay;
    }

    @Override
    public Set<GameDisplay> getAll() {
        Set<Game> games = this.gameRepo.findAll();
        Set<GameDisplay> gamesToDisplay = new LinkedHashSet<>();
        for (Game game : games) {
            GameDisplay gameToDisplay = this.modelParser.convert(game, GameDisplay.class);
            gamesToDisplay.add(gameToDisplay);
        }
        return gamesToDisplay;
    }

    @Override
    public Set<GameDisplay> getOwnedGames(Long id) {
        Set<Game> games = this.userRepo.getGamesOfUser(id);
        Set<GameDisplay> gamesToDisplay = new LinkedHashSet<>();
        for (Game game : games) {
            GameDisplay gameToDisplay = this.modelParser.convert(game, GameDisplay.class);
            gamesToDisplay.add(gameToDisplay);
        }
        return gamesToDisplay;
    }

}
