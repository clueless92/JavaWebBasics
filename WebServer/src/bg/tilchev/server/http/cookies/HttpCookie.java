package bg.tilchev.server.http.cookies;

/**
 * Created on 2017-02-12.
 */
public interface HttpCookie {

    void addCookie(String key, String value);

    void removeCookie(String key);

    boolean contains(String key);

    String getCookie(String key);
}
