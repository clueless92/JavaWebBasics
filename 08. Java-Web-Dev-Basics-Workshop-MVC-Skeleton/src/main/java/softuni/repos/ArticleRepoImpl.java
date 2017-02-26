package softuni.repos;

import softuni.models.Article;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(ArticleRepo.class)
public class ArticleRepoImpl implements ArticleRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Article retrive(Long id) {
        return this.entityManager.find(Article.class, id);
    }

    @Override
    public void persist(Article article) {
        this.entityManager.persist(article);
    }

    @Override
    public void update(Article article) {
        this.entityManager.refresh(article); // TODO
    }

    @Override
    public void delete(Article article) {
        this.entityManager.remove(article); // TODO
    }

    @Override
    public boolean exists(Long id) {
        Article article = this.entityManager.find(Article.class, id);
        return article != null;
    }
}
