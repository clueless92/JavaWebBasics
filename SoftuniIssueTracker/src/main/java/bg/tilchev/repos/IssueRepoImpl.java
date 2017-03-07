package bg.tilchev.repos;

import bg.tilchev.models.entities.Issue;
import bg.tilchev.models.enums.Status;
import bg.tilchev.repos.interfaces.IssueRepo;

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
public class IssueRepoImpl implements IssueRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(Issue issue) {
        this.entityManager.persist(issue);
    }

    @Override
    public void update(Issue issue) {
        this.entityManager.merge(issue);
    }

    @Override
    public Issue findById(Long id) {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i WHERE i.id = :id");
        query.setParameter("id", id);
        List<Issue> issues = query.getResultList();
        if (issues == null || issues.isEmpty()) {
            return null;
        }
        return issues.get(0);
    }

    @Override
    public void deleteById(Long id) {
        String jpql = "DELETE FROM Issue AS i WHERE i.id = :id";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Set<Issue> findAll() {
        String jpql = "SELECT i FROM Issue AS i ORDER BY i.id ASC";
        Query query = this.entityManager.createQuery(jpql);
        return new LinkedHashSet<>(query.getResultList());
    }

    @Override
    public Set<Issue> findAllWith(String name, String status) {
        String jpql = "SELECT i FROM Issue AS i WHERE i.name LIKE :name AND i.status = :status ORDER BY i.id ASC";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("name", "%" + name + "%");
        query.setParameter("status", Status.valueOf(status.toUpperCase()));
        return new LinkedHashSet<>(query.getResultList());
    }

    @Override
    public Set<Issue> findAllWith(String name) {
        String jpql = "SELECT i FROM Issue AS i WHERE i.name LIKE :name ORDER BY i.id ASC";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("name", "%" + name + "%");
        return new LinkedHashSet<>(query.getResultList());
    }
}
