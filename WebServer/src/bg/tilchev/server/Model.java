package bg.tilchev.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class Model {

    private Map<String, Object> objects;

    public Model() {
        this.objects = new HashMap<>();
    }

    public void add(String key, Object value) {
        this.objects.put(key, value);
    }

    public Object get(String key) {
        return this.objects.get(key);
    }
}
