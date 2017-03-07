package bg.tilchev.utils;

/**
 * Created on 2017-03-03.
 */
public interface Listen {

    String HOME = "/";
    String ISSUES = "/issues";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String REGISTER = "/register";
    String ISSUES_EDIT = ISSUES + "/edit/{id}";
    String ISSUES_DELETE = ISSUES + "/delete/{id}";
    String ISSUES_ADD = ISSUES + "/add";
    String ISSUES_FILTER = ISSUES + "/*";
}
