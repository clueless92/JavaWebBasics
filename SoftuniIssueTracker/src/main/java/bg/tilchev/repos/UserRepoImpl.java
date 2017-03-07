package bg.tilchev.repos;

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
    public User findByUsername(String username) {
        String jpql = "SELECT u FROM User AS u WHERE u.username = :username";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String jpql = "SELECT u FROM User AS u WHERE u.username = :username AND u.password = :password";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("username", username);
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
}
