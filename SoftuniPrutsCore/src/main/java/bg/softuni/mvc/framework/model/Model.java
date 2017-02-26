package bg.softuni.mvc.framework.model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Model {

    private HttpServletRequest request;
    private Map<String, Object> attributes;

    public Model(HttpServletRequest request) {
        this.request = request;
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
        this.sendParamsToView();
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    private void sendParamsToView() {
        for (Map.Entry<String, Object> entry : this.attributes.entrySet()) {
            this.request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
