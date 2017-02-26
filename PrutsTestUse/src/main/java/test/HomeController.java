package test;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.request.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "home";
    }
}
