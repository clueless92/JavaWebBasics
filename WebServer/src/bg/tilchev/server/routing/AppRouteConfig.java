package bg.tilchev.server.routing;

import bg.tilchev.server.handler.RequestHandler;
import bg.tilchev.server.http.request.HttpRequestType;

import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public interface AppRouteConfig {

    AppRouteConfig addRoute(String path, RequestHandler handler);

    Iterable<Map.Entry<HttpRequestType, Map<String, RequestHandler>>> getRoutes();
}
