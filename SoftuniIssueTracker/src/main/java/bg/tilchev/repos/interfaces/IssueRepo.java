package bg.tilchev.repos.interfaces;

import bg.tilchev.models.entities.Issue;

import java.util.Set;

/**
 * Created on 2017-03-02.
 */
public interface IssueRepo {

    void persist(Issue issue);

    void update(Issue issue);

    Issue findById(Long id);

    void deleteById(Long id);

    Set<Issue> findAll();

    Set<Issue> findAllWith(String name, String status);

    Set<Issue> findAllWith(String name);
}
