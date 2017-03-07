package bg.tilchev.repos.interfaces;

import bg.tilchev.models.entities.Game;

import java.util.Set;

public interface GameRepo {

    void persist(Game game);

    void update(Game game);

    Game findById(Long id);

    void deleteById(Long id);

    Set<Game> findAll();

//    Set<Game> findAllWith(String title);
}
