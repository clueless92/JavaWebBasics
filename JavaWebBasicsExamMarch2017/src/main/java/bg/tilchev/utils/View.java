package bg.tilchev.utils;

/**
 * Created on 2017-03-02.
 */
public enum View {

    LOGIN(Template.LOGIN, Listen.LOGIN),
    REGISTER(Template.REGISTER, Listen.REGISTER),
    HOME(Template.HOME, Listen.HOME),
    GAMES(Template.ADMIN_GAMES, Listen.GAMES),
    GAMES_ADD(Template.ADD_GAME, Listen.GAMES_ADD),
    GAMES_DELETE(Template.DELETE_GAME, Listen.GAMES_DELETE),
    GAMES_EDIT(Template.EDIT_GAME, Listen.GAMES_EDIT),
    GAME_INFO(Template.GAME_INFO, Listen.GAME_INFO),
    CART(Template.CART, Listen.CART),
    ;

    private static final String VIEW_PREFIX = "WEB-INF/templates";
    private static final String REDIRECT = "redirect:";

    private String template;
    private String path;

    View(String template, String path) {
        this.template = template;
        this.path = path;
    }

    public String get() {
        return VIEW_PREFIX + this.template;
    }

    public String redirect() {
        return REDIRECT + this.path;
    }
}
