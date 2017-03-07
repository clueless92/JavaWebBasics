package bg.tilchev.services.interfaces;

import bg.tilchev.models.binding.GameBindingModel;
import bg.tilchev.models.view.GameDisplay;
import bg.tilchev.models.view.UserCurrent;

import java.util.Set;

/**
 * Created on 2017-03-03.
 */
public interface GameService {

    void submit(GameBindingModel issueAddEdit);

    void edit(GameBindingModel issueAddEdit);

    void delete(Long id);

    GameDisplay getGame(Long id);

    Set<GameDisplay> getAll();

    Set<GameDisplay> getOwnedGames(Long id);
}
