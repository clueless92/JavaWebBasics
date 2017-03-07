package bg.tilchev.utils;

/**
 * Created on 2017-03-03.
 */
public interface Listen {

    String HOME = "/";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String REGISTER = "/register";
    String GAMES = "/games";
    String GAMES_EDIT = GAMES + "/edit/{id}";
    String GAMES_DELETE = GAMES + "/delete/{id}";
    String GAMES_ADD = GAMES + "/add";
    String GAME_INFO = "/game/info/{id}";
    String GAME_BUY = "/game/buy/{id}";
    String GAME_UNBUY = "/game/unbuy/{id}";
    String CART = "/cart";
}
