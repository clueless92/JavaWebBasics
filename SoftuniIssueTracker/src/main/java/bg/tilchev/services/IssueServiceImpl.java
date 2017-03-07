package bg.tilchev.services;

import bg.tilchev.models.binding.IssueAddEdit;
import bg.tilchev.models.entities.Issue;
import bg.tilchev.models.entities.User;
import bg.tilchev.models.enums.Priority;
import bg.tilchev.models.enums.Status;
import bg.tilchev.models.view.IssueDisplay;
import bg.tilchev.repos.interfaces.IssueRepo;
import bg.tilchev.repos.interfaces.UserRepo;
import bg.tilchev.services.interfaces.IssueService;
import bg.tilchev.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created on 2017-03-03.
 */
@Stateless
public class IssueServiceImpl implements IssueService {

    @Inject
    private IssueRepo issueRepo;

    @Inject
    private UserRepo userRepo;

    @Inject
    private ModelParser modelParser;

    @Override
    public void submit(IssueAddEdit issueAddEdit) {
        User author = this.userRepo.findByUsername(issueAddEdit.getAuthorName());
        Issue issue = this.modelParser.convert(issueAddEdit, Issue.class);
        issue.setPriority(Priority.valueOf(issueAddEdit.getPriority().toUpperCase()));
        issue.setStatus(Status.valueOf(issueAddEdit.getStatus().toUpperCase()));
        issue.setCreatedOn(new Date());
        issue.setAuthor(author);
        this.issueRepo.persist(issue);
    }

    @Override
    public void edit(IssueAddEdit issueAddEdit) {
        Issue issue = this.modelParser.convert(issueAddEdit, Issue.class);
        issue.setPriority(Priority.valueOf(issueAddEdit.getPriority().toUpperCase()));
        issue.setStatus(Status.valueOf(issueAddEdit.getStatus().toUpperCase()));
        this.issueRepo.update(issue);
    }

    @Override
    public void delete(Long id) {
        this.issueRepo.deleteById(id);
    }

    @Override
    public IssueDisplay getIssue(Long id) {
        Issue issue = this.issueRepo.findById(id);
        IssueDisplay issueDisplay = this.modelParser.convert(issue, IssueDisplay.class);
        return issueDisplay;
    }

    @Override
    public Set<IssueDisplay> getAll() {
        Set<Issue> issues = this.issueRepo.findAll();
        Set<IssueDisplay> issuesToDisplay = new LinkedHashSet<>();
        for (Issue issue : issues) {
            IssueDisplay issueDisplay = this.modelParser.convert(issue, IssueDisplay.class);
            issueDisplay.setAuthorName(issue.getAuthor().getUsername());
            issuesToDisplay.add(issueDisplay);
        }
        return issuesToDisplay;
    }

    @Override
    public Set<IssueDisplay> filterIssues(String name, String status) {
        Set<Issue> issues = this.issueRepo.findAllWith(name, status);
        Set<IssueDisplay> issuesToDisplay = new LinkedHashSet<>();
        for (Issue issue : issues) {
            IssueDisplay issueDisplay = this.modelParser.convert(issue, IssueDisplay.class);
            issueDisplay.setAuthorName(issue.getAuthor().getUsername());
            issuesToDisplay.add(issueDisplay);
        }
        return issuesToDisplay;
    }

    @Override
    public Set<IssueDisplay> filterIssues(String name) {
        Set<Issue> issues = this.issueRepo.findAllWith(name);
        Set<IssueDisplay> issuesToDisplay = new LinkedHashSet<>();
        for (Issue issue : issues) {
            IssueDisplay issueDisplay = this.modelParser.convert(issue, IssueDisplay.class);
            issueDisplay.setAuthorName(issue.getAuthor().getUsername());
            issuesToDisplay.add(issueDisplay);
        }
        return issuesToDisplay;
    }
}
