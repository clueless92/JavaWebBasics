package bg.tilchev.controllers;

import bg.tilchev.models.binding.IssueAddEdit;
import bg.tilchev.models.binding.UserRegister;
import bg.tilchev.models.enums.Role;
import bg.tilchev.models.view.IssueDisplay;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.services.interfaces.IssueService;
import bg.tilchev.utils.Listen;
import bg.tilchev.utils.View;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

/**
 * Created on 2017-03-02.
 */
@Stateless
@Controller
public class IssueController {

    @Inject
    private IssueService issueService;

    @GetMapping(Listen.ISSUES)
    public String getIssues(Model model, @RequestParam("status") String status, @RequestParam("name") String name) {
        Set<IssueDisplay> issuesToDisplay;
        if (status == null || status.equalsIgnoreCase("ALL")) {
            if (name == null || name.isEmpty()) {
                issuesToDisplay = this.issueService.getAll();
            } else {
                issuesToDisplay = this.issueService.filterIssues(name);
            }
        } else {
            issuesToDisplay = this.issueService.filterIssues(name, status);
        }
        model.addAttribute("issues", issuesToDisplay);
        return View.ISSUES.get();
    }

    @GetMapping(Listen.ISSUES_ADD)
    public String getAdd() {
        return View.ADD_ISSUE.get();
    }

    @PostMapping(Listen.ISSUES_ADD)
    public String doAdd(@ModelAttribute IssueAddEdit issueAddEdit, HttpSession session, Model model) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<IssueAddEdit>> constraintViolations = validator.validate(issueAddEdit);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<IssueAddEdit> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getMessage());
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return View.ADD_ISSUE.get();
        }
        UserCurrent user = (UserCurrent) session.getAttribute("user");
        issueAddEdit.setAuthorName(user.getUsername());
        this.issueService.submit(issueAddEdit);
        return View.ISSUES.redirect();
    }

    @GetMapping(Listen.ISSUES_EDIT)
    public String getEdit(@PathVariable("id") Long id, Model model, HttpSession session) {
        UserCurrent user = (UserCurrent) session.getAttribute("user");
        IssueDisplay issueDisplay = this.issueService.getIssue(id);
        if (!user.getUsername().equals(issueDisplay.getAuthorName()) && user.getRole() != Role.ADMIN) {
            return View.ISSUES.redirect();
        }
        model.addAttribute("issue", issueDisplay);
        return View.EDIT_ISSUE.get();
    }

    @PostMapping(Listen.ISSUES_EDIT)
    public String doEdit(@PathVariable("id") Long id, @ModelAttribute IssueAddEdit issueAddEdit) {
        issueAddEdit.setId(id);
        this.issueService.edit(issueAddEdit);
        return View.ISSUES.redirect();
    }

    @GetMapping(Listen.ISSUES_DELETE)
    public String getDelete(@PathVariable("id") Long id, Model model, HttpSession session) {
        UserCurrent user = (UserCurrent) session.getAttribute("user");
        IssueDisplay issueDisplay = this.issueService.getIssue(id);
        if (!user.getUsername().equals(issueDisplay.getAuthorName()) && user.getRole() != Role.ADMIN) {
            return View.ISSUES.redirect();
        }
        model.addAttribute("issue", issueDisplay);
        return View.DELETE_ISSUE.get();
    }

    @PostMapping(Listen.ISSUES_DELETE)
    public String doEdit(@PathVariable("id") Long id) {
        this.issueService.delete(id);
        return View.ISSUES.redirect();
    }
}
