package bg.tilchev.server.routing;

import bg.tilchev.server.handler.RequestHandler;
import bg.tilchev.server.http.request.HttpRequestType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class AppRouteConfigImpl implements AppRouteConfig {

    private Map<HttpRequestType, Map<String, RequestHandler>> routes;

    public AppRouteConfigImpl() {
        this.routes = new HashMap<>();
        for (HttpRequestType httpRequestType : HttpRequestType.values()) {
            this.routes.put(httpRequestType, new HashMap<>());
        }
    }

    @Override
    public AppRouteConfig addRoute(String path, RequestHandler handler) {
        // TODO: maybe replace .toString() with .getSimpleName()
        boolean isGet = handler.getClass().toString().toLowerCase().contains("get");
        boolean isPost = handler.getClass().toString().toLowerCase().contains("post");
        if(isGet) {
            this.routes.get(HttpRequestType.GET).put(path, handler);
        } else if (isPost) {
            this.routes.get(HttpRequestType.POST).put(path, handler);
        }
        return this;
    }

    @Override
    public Iterable<Map.Entry<HttpRequestType, Map<String, RequestHandler>>> getRoutes() {
        return this.routes.entrySet();
    }
}
