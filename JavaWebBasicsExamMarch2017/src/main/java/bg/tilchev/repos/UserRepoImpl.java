package bg.tilchev.repos;

import bg.tilchev.models.entities.Game;
import bg.tilchev.models.entities.User;
import bg.tilchev.repos.interfaces.UserRepo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2017-03-02.
 */

@Stateless
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User findById(Long id) {
        String jpql = "SELECT u FROM User AS u WHERE u.id = :id";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User AS u WHERE u.email = :email";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT u FROM User AS u WHERE u.email = :email AND u.password = :password";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public Set<User> findAll() {
        String jpql = "SELECT u FROM User AS u";
        Query query = this.entityManager.createQuery(jpql);
        Set<User> users = new LinkedHashSet<>(query.getResultList());
        return users;
    }

    @Override
    public void update(User user) {
        this.entityManager.merge(user);
    }

    @Override
    public Set<Game> getGamesOfUser(Long id) {
        String jpql = "SELECT u.games FROM User AS u WHERE u.id = :id";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("id", id);
        Set<Game> games = new LinkedHashSet<>(query.getResultList());
        return games;
    }
}
