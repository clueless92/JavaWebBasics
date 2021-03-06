package bg.tilchev.server.http.cookies;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class HttpCookieImpl implements HttpCookie {

    private Map<String, String> cookies;

    public HttpCookieImpl() {
        this.cookies = new HashMap<>();
    }

    @Override
    public void addCookie(String key, String value) {
        this.cookies.put(key, value);
    }

    @Override
    public void removeCookie(String key) {
        this.cookies.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return this.cookies.containsKey(key);
    }

    @Override
    public String getCookie(String key) {
        return this.cookies.get(key);
    }
}
