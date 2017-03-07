package bg.tilchev.services.interfaces;

import bg.tilchev.models.binding.IssueAddEdit;
import bg.tilchev.models.entities.Issue;
import bg.tilchev.models.view.IssueDisplay;

import java.util.Set;

/**
 * Created on 2017-03-03.
 */
public interface IssueService {

    void submit(IssueAddEdit issueAddEdit);

    void edit(IssueAddEdit issueAddEdit);

    void delete(Long id);

    IssueDisplay getIssue(Long id);

    Set<IssueDisplay> getAll();

    Set<IssueDisplay> filterIssues(String name, String status);

    Set<IssueDisplay> filterIssues(String name);
}
