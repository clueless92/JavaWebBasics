package bg.softuni.mvc.framework.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerActionPair {

    private Class controllerType;
    private Method actionMethod;
    private Map<String, String> pathVariables;

    public ControllerActionPair(Class controllerType, Method actionMethod) {
        this.controllerType = controllerType;
        this.actionMethod = actionMethod;
        this.pathVariables = new HashMap<>();
    }

    public void addPathVariable(String key, String value) {
        this.pathVariables.put(key, value);
    }

    public String getPathVariable(String key) {
        return this.pathVariables.get(key);
    }

    public Class getControllerType() {
        return this.controllerType;
    }

    public Method getActionMethod() {
        return this.actionMethod;
    }
}
