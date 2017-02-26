package bg.tilchev.server.http.session;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class HttpSessionImpl implements HttpSession {

    private String id;
    private Map<String, String> params;

    public HttpSessionImpl(String id) {
        this.id = id;
        this.params = new HashMap<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void clear() {
        this.params.clear();
    }

    @Override
    public void add(String key, String value) {
        this.params.put(key, value);
    }

    @Override
    public String get(String key) {
        return this.params.get(key);
    }

    @Override
    public boolean isAuthenticated() {
        return this.params.size() > 0;
    }
}
