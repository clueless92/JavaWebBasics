package bg.tilchev.utils;

/**
 * Created on 2017-03-02.
 */
public enum View {

    LOGIN(Listen.LOGIN, Listen.LOGIN),
    REGISTER(Listen.REGISTER, Listen.REGISTER),
    HOME(Template.HOME, Listen.HOME),
    ISSUES(Listen.ISSUES, Listen.ISSUES),
    LOGOUT(Listen.LOGOUT, Listen.LOGOUT),
    ADD_ISSUE(Template.ADD_ISSUE, Listen.ISSUES_ADD),
    EDIT_ISSUE(Template.EDIT_ISSUE, Listen.ISSUES_EDIT),
    DELETE_ISSUE(Template.DELETE_ISSUE, Listen.ISSUES_DELETE);

    private static final String VIEW_PREFIX = "WEB-INF/templates";
    private static final String REDIRECT = "redirect:";

    private String template;
    private String path;

    View(String template) {
        this.template = template;
    }

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
