package softuni.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class Model {
    private Map<String, Object> objects;

    public Model() {
        this.objects = new HashMap<>();
    }

    public void add(String key, Object value){
        this.objects.put(key, value);
    }

    public Object get(String key){
        return this.objects.get(key);
    }
}
