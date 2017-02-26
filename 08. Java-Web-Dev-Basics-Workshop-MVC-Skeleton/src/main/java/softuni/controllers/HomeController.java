package softuni.controllers;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.request.GetMapping;
import bg.softuni.mvc.framework.model.Model;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {

    @GetMapping("/")
    public String details(Model model){
        model.addAttribute("title", "Softuni Blog");
        model.addAttribute("view", "home/index.jsp");
        return "base-layout";
    }
}
