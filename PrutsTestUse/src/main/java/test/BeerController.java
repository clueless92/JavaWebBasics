package test;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.parameters.PathVariable;
import bg.softuni.mvc.framework.annotations.parameters.RequestParam;
import bg.softuni.mvc.framework.annotations.request.GetMapping;
import bg.softuni.mvc.framework.annotations.request.PostMapping;
import bg.softuni.mvc.framework.model.Model;

/**
 * Created on 2017-02-21.
 */
@Controller
public class BeerController {

    @GetMapping("/beer")
    public String getBeerPage(Model model) {
        model.addAttribute("key", "ModelTest");
        return "beer";
    }

    @PostMapping("/beer")
    public String submitBeer(@RequestParam("beerBrand") int beerBrand) {
        System.out.println(beerBrand);
        return "redirect:/test";
    }

    @GetMapping("/beer/edit/{id}")
    public String editBeerPage(@PathVariable("id") Integer id) {
//        this.beerService.save(id); // Example use
        System.out.println(id);
        return "beer";
    }
}
