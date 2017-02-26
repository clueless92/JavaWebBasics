package bg.tilchev.server.http.request;

import bg.tilchev.server.http.cookies.HttpCookie;
import bg.tilchev.server.http.session.HttpSession;

import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public interface HttpRequest {

    String getUrl();

    String getPath();

    HttpRequestType getRequestType();

    String getHeader(String name);

    Map<String, String> getUrlParams();

    Map<String, String> getQueryParams();

    Map<String, String> getFormData();

    void addParam(String name, String value);

    void setSession(HttpSession session);

    HttpSession getSession();

    HttpCookie getCookie();

    void setSession(Map<String, HttpSession> sessionMap);
}
