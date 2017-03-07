package bg.tilchev.controllers;

import bg.tilchev.models.view.GameDisplay;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.services.interfaces.GameService;
import bg.tilchev.utils.Listen;
import bg.tilchev.utils.View;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created on 2017-03-02.
 */
@Stateless
@Controller
public class HomeController {

    @Inject
    private GameService gameService;

    @GetMapping(Listen.HOME)
    public String getHomePage(@RequestParam("filter") String filter, HttpSession session, Model model) {
        UserCurrent user = (UserCurrent) session.getAttribute("user");
        Set<GameDisplay> gamesToDisplay = new LinkedHashSet<>();
        if (user == null || filter == null || filter.isEmpty() || "all".equalsIgnoreCase(filter)) {
            gamesToDisplay = this.gameService.getAll();
        }
        if (user != null && "owned".equalsIgnoreCase(filter)) {
            gamesToDisplay = this.gameService.getOwnedGames(user.getId());
        }
        model.addAttribute("games", gamesToDisplay);
        return View.HOME.get();
    }
}
