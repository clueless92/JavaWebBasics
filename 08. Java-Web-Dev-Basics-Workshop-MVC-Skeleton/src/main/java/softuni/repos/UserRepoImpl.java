package softuni.repos;

import softuni.models.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepo.class)
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User retrieve(Long id) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User retrieve(String email) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean exists(String email) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user != null;
    }
}
