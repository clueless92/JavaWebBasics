package softuni.server.routing;

import softuni.server.handler.RequestHandlerImpl;
import softuni.server.http.HttpRequestMethod;

import java.util.Map;

public interface AppRouteConfig {
    AppRouteConfig addRoute(String path, RequestHandlerImpl handler);

    Iterable<Map.Entry<HttpRequestMethod,
            Map<String, RequestHandlerImpl>>> getRoutes();
}
