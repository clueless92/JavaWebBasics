package bg.tilchev.server.http.session;

/**
 * Created on 2017-02-12.
 */
public interface HttpSession {

    String getId();

    void clear();

    void add(String key, String value);

    String get(String key);

    boolean isAuthenticated();
}
