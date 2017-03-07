package bg.tilchev.controllers;

import bg.tilchev.models.binding.UserLogin;
import bg.tilchev.models.binding.UserRegister;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.services.interfaces.UserService;
import bg.tilchev.utils.Listen;
import bg.tilchev.utils.View;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created on 2017-03-02.
 */
@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @GetMapping(Listen.LOGIN)
    public String getLoginPage() {
        return View.LOGIN.get();
    }

    @GetMapping(Listen.REGISTER)
    public String getRegisterPage() {
        return View.REGISTER.get();
    }

    @PostMapping(Listen.REGISTER)
    public String registerUser(@ModelAttribute UserRegister userRegister, Model model) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRegister>> constraintViolations = validator.validate(userRegister);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<UserRegister> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getMessage());
        }
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            errors.add("Passwords don't match");
        }
        if (this.userService.exists(userRegister.getUsername())) {
            errors.add("Username already taken");
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return View.REGISTER.get();
        }
        this.userService.registerUser(userRegister);
        return View.LOGIN.redirect();
    }

    @PostMapping(Listen.LOGIN)
    public String loginUser(@ModelAttribute UserLogin userLogin, Model model, HttpSession session) {
        UserCurrent user = this.userService.getCurrentUser(userLogin);
        List<String> errors = new ArrayList<>();
        if (null == user) {
            errors.add("No such user or invalid username and/or password.");
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return View.LOGIN.get();
        }
        session.setAttribute("user", user);
        return View.ISSUES.redirect();
    }

    @GetMapping(Listen.LOGOUT)
    public String logout(HttpSession session) {
        session.invalidate();
        return View.LOGIN.redirect();
    }
}
