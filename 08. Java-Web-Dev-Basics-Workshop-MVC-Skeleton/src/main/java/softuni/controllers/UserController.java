package softuni.controllers;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.parameters.RequestParam;
import bg.softuni.mvc.framework.annotations.request.GetMapping;
import bg.softuni.mvc.framework.annotations.request.PostMapping;
import bg.softuni.mvc.framework.model.Model;
import softuni.models.User;
import softuni.repos.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserRepo userRepo;

    public UserController() {
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("view", "user/register.jsp");
        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm-password") String confirmPassword,
            @RequestParam("full-name") String fullName,
            Model model) {
        List<String> errors = new ArrayList<>();
        this.validatePassword(password, confirmPassword, errors);
        this.validateFullName(fullName, errors);
        this.validateEmail(email, errors);
        if(errors.size() == 0) {
            User user1 = new User(email, fullName, password);
            this.userRepo.persist(user1);
            return "redirect:/login";
        } else {
            model.addAttribute("errors", errors);
            model.addAttribute("title", "Register");
            model.addAttribute("view", "user/register.jsp");
            return "base-layout";
        }
    }

    private void validateEmail(String email, List<String> errors) {
        if(!email.contains("@") || !email.split("@")[1].contains(".")) {
            errors.add("Invalid Email");
        }
    }

    private void validateFullName(String fullName, List<String> errors) {
        if(fullName.length() >= 3 && fullName.length() <= 50 && fullName.trim().length() != 0) {
            Pattern user = Pattern.compile("[a-zA-Z -]+");
            Matcher matcher = user.matcher(fullName);
            if(!matcher.find()) {
                errors.add("Full name contains invalid characters");
            }
        } else {
            errors.add("Full name must be between 3 and 50 characters");
        }
    }

    private void validatePassword(String password, String confirmPassword, List<String> errors) {
        if(!password.equals(confirmPassword)) {
            errors.add("Passwords do not match");
        } else if(password.length() < 3 && password.length() > 30) {
            errors.add("Password must be between 3 and 30 symbols");
        }
    }
}
