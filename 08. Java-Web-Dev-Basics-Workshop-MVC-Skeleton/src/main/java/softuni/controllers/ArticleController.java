package softuni.controllers;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.parameters.PathVariable;
import bg.softuni.mvc.framework.annotations.request.GetMapping;
import bg.softuni.mvc.framework.model.Model;
import softuni.repos.ArticleRepo;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Controller
public class ArticleController {

    @Inject
    private ArticleRepo articleRepo;

    public ArticleController() {
    }

    @GetMapping("/article/{id}")
    public String details(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Article Details");
        model.addAttribute("view", "article/details.jsp");
        return "base-layout";
    }

    @GetMapping("/article/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Edit Article");
        model.addAttribute("view", "article/edit.jsp");
        return "base-layout";
    }

    @GetMapping("/article/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Delete Article");
        model.addAttribute("view", "article/delete.jsp");
        return "base-layout";
    }

    @GetMapping("/article/create")
    public String create(Model model) {
        model.addAttribute("title", "Create New Article");
        model.addAttribute("view", "article/create.jsp");
        return "base-layout";
    }
}
