package softuni.server.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class HttpSessionImpl implements HttpSession{


    private String id;
    private Map<String, String> parameters;

    public HttpSessionImpl(String id) {
        this.parameters = new HashMap<>();
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void clear() {
        this.parameters.clear();
    }

    @Override
    public void add(String key, String value) {
        this.parameters.put(key, value);
    }

    @Override
    public String get(String key) {
        return this.parameters.get(key);
    }

    @Override
    public boolean isAuthenticated() {
        return this.parameters.size() > 0;
    }
}
