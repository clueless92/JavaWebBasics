package bg.tilchev.repository;

import bg.tilchev.entities.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepo.class)
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User getByUsername(String username) {
//        EntityTransaction transaction = this.entityManager.getTransaction();
//        transaction.begin();
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
