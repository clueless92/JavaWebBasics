package softuni.repos;

import softuni.models.Article;

public interface ArticleRepo {

    Article retrive(Long id);

    void persist(Article article);

    void update(Article article);

    void delete(Article article);

    boolean exists(Long id);
}
