package bg.tilchev.controllers;

import bg.tilchev.models.binding.GameBindingModel;
import bg.tilchev.models.view.GameDisplay;
import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.services.interfaces.GameService;
import bg.tilchev.services.interfaces.UserService;
import bg.tilchev.utils.DataValidator;
import bg.tilchev.utils.Listen;
import bg.tilchev.utils.View;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created on 2017-03-05.
 */
@Stateless
@Controller
public class GameController {

    @Inject
    private GameService gameService;

    @Inject
    private UserService userService;

    @Inject
    private DataValidator dataValidator;

    @GetMapping(Listen.GAMES)
    public String getAdminGames(Model model) {
        Set<GameDisplay> games = this.gameService.getAll();
        model.addAttribute("games", games);
        return View.GAMES.get();
    }

    @GetMapping(Listen.GAMES_ADD)
    public String getAddGame() {
        return View.GAMES_ADD.get();
    }

    @PostMapping(Listen.GAMES_ADD)
    public String doAddGame(@ModelAttribute GameBindingModel gameBindingModel, Model model) {
        Set<String> errors = this.dataValidator.validateData(gameBindingModel);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return View.GAMES_ADD.get();
        }
        this.gameService.submit(gameBindingModel);
        return View.GAMES.redirect();
    }

    @GetMapping(Listen.GAMES_EDIT)
    public String getEditGame(@PathVariable("id") Long id, Model model) {
        GameDisplay gameDisplay = this.gameService.getGame(id);
        model.addAttribute("game", gameDisplay);
        return View.GAMES_EDIT.get();
    }

    @PostMapping(Listen.GAMES_EDIT)
    public String doEditGame(@PathVariable("id") Long id, @ModelAttribute GameBindingModel gameBindingModel, Model model) {
        gameBindingModel.setId(id);
        Set<String> errors = this.dataValidator.validateData(gameBindingModel);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return View.GAMES_EDIT.get();
        }
        this.gameService.edit(gameBindingModel);
        return View.GAMES.redirect();
    }

    @GetMapping(Listen.GAMES_DELETE)
    public String getDeleteGame(@PathVariable("id") Long id, Model model) {
        GameDisplay gameDisplay = this.gameService.getGame(id);
        model.addAttribute("game", gameDisplay);
        return View.GAMES_DELETE.get();
    }

    @PostMapping(Listen.GAMES_DELETE)
    public String doDeleteGame(@PathVariable("id") Long id) {
        this.gameService.delete(id);
        return View.GAMES.redirect();
    }

    @GetMapping(Listen.GAME_INFO)
    public String getInfoGame(@PathVariable("id") Long id, Model model) {
        GameDisplay gameDisplay = this.gameService.getGame(id);
        model.addAttribute("game", gameDisplay);
        return View.GAME_INFO.get();
    }

    @GetMapping(Listen.GAME_BUY)
    public String addGameToCart(@PathVariable("id") Long id, HttpSession session) {
        GameDisplay gameDisplay = this.gameService.getGame(id);
        Set<GameDisplay> cart = (Set<GameDisplay>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashSet<>();
        }
        cart.add(gameDisplay);
        session.setAttribute("cart", cart);
        return "redirect:" + Listen.HOME;
    }

    @GetMapping(Listen.CART)
    public String getCart(HttpSession session, Model model) {
        Set<GameDisplay> cart = (Set<GameDisplay>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashSet<>();
        }
        model.addAttribute("games", cart);
        Double total = 0d;
        for (GameDisplay gameDisplay : cart) {
            total += gameDisplay.getPrice();
        }
        model.addAttribute("total", String.format("%.2f", total));
        return View.CART.get();
    }

    @GetMapping(Listen.GAME_UNBUY)
    public String removeGameFromCart(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return View.LOGIN.redirect();
        }
        Set<GameDisplay> cart = (Set<GameDisplay>) session.getAttribute("cart");
        if (cart == null) {
            return View.CART.redirect();
        }
        GameDisplay gameToRemove = null;
        for (GameDisplay display : cart) {
            if (display.getId().equals(id)) {
                gameToRemove = display;
                break;
            }
        }
        cart.remove(gameToRemove);
        session.setAttribute("games", cart);

        return View.CART.redirect();
    }

    @PostMapping(Listen.CART)
    public String submitOrder(HttpSession session, Model model) {
        Set<GameDisplay> cart = (Set<GameDisplay>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashSet<>();
        }
        UserCurrent user = (UserCurrent) session.getAttribute("user");
        this.userService.buyGames(user, cart);
        Double total = 0d;
        cart = new LinkedHashSet<>();
        session.setAttribute("cart", cart);
        model.addAttribute("games", cart);
        model.addAttribute("user", user);
        for (GameDisplay gameDisplay : cart) {
            total += gameDisplay.getPrice();
        }
        model.addAttribute("total", String.format("%.2f", total));
        return View.HOME.redirect();
    }
}
