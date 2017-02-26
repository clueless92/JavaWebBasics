package bg.tilchev.server.routing;

import bg.tilchev.server.http.request.HttpRequestType;

import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public interface ServerRouteConfig {

    Map<HttpRequestType, Map<String, RoutingContext>> getRoutes();
}
