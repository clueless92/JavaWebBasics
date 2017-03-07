package bg.tilchev.repos;

import bg.tilchev.models.entities.Game;
import bg.tilchev.repos.interfaces.GameRepo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2017-03-05.
 */
@Stateless
public class GameRepoImpl implements GameRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(Game game) {
        this.entityManager.persist(game);
    }

    @Override
    public void update(Game game) {
        this.entityManager.merge(game);
    }

    @Override
    public Game findById(Long id) {
        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g WHERE g.id = :id");
        query.setParameter("id", id);
        List<Game> games = query.getResultList();
        if (games == null || games.isEmpty()) {
            return null;
        }
        return games.get(0);
    }

    @Override
    public void deleteById(Long id) {
        String jpql = "DELETE FROM Game AS g WHERE g.id = :id";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Set<Game> findAll() {
        String jpql = "SELECT g FROM Game AS g ORDER BY g.id ASC";
        Query query = this.entityManager.createQuery(jpql);
        return new LinkedHashSet<>(query.getResultList());
    }

}
