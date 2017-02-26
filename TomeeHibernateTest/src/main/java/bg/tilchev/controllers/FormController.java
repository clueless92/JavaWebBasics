package bg.tilchev.controllers;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.parameters.ModelAttribute;
import bg.softuni.mvc.framework.annotations.parameters.PathVariable;
import bg.softuni.mvc.framework.annotations.parameters.RequestParam;
import bg.softuni.mvc.framework.annotations.request.GetMapping;
import bg.softuni.mvc.framework.annotations.request.PostMapping;
import bg.softuni.mvc.framework.model.Model;
import bg.tilchev.entities.User;
import bg.tilchev.repository.UserRepo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Stateless
@Controller
public class FormController {

    @Inject
    private UserRepo userRepo;

    @GetMapping("/users")
    public String getUsersPage(){
        return "users";
    }

    @GetMapping("/")
    public String getHomePage(HttpServletResponse response){
        response.addCookie(new Cookie("lang", "en"));
        return "redirect:/users";
    }

//    @PostMapping("/users")
//    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
//        User user = new User(username, password);
//        this.userRepo.create(user);
//        return "users";
//    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute User user, HttpSession session){
        this.userRepo.create(user);
        session.setAttribute("user", user);
        return "redirect:/users/" + user.getUsername();
    }

    @GetMapping("/users/{username}")
    public String getUser(Model model, @PathVariable("username") String username) {
        User user = this.userRepo.getByUsername(username);
        model.addAttribute("username", user.getUsername());
        return "users";
    }
}
