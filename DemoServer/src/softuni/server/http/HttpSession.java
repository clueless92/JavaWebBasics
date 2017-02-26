package softuni.server.http;

/**
 * Created by s_she on 09.2.2017 г..
 */
public interface HttpSession {
    String getId();

    void clear();

    void add(String key, String value);
    String get(String key);

    boolean isAuthenticated();

}
