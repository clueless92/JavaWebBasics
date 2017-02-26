package softuni.server.routing;

import softuni.server.http.HttpRequestMethod;

import java.util.Map;

/**
 * Created by s_she on 09.2.2017 г..
 */
public interface ServerRouteConfig {
    Map<HttpRequestMethod, Map<String, RoutingContext>> getRoutes();

}
