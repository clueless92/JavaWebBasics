package bg.tilchev.controllers;

import bg.tilchev.utils.Listen;
import bg.tilchev.utils.View;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;

import javax.ejb.Stateless;

/**
 * Created on 2017-03-02.
 */
@Stateless
@Controller
public class HomeController {

    @GetMapping(Listen.HOME)
    public String getHomePage() {
        return View.HOME.get();
    }
}
