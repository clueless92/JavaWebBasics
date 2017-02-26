package bg.tilchev.server.routing;

import bg.tilchev.server.Server;
import bg.tilchev.server.handler.RequestHandler;
import bg.tilchev.server.http.request.HttpRequest;
import bg.tilchev.server.http.request.HttpRequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017-02-12.
 */
public class ServerRouteConfigImpl implements ServerRouteConfig {

    private Map<HttpRequestType, Map<String, RoutingContext>> routes;

    public ServerRouteConfigImpl(AppRouteConfig appRouteConfig) {
        this.routes = new HashMap<>();
        for (HttpRequestType type : HttpRequestType.values()) {
            this.routes.put(type, new HashMap<>());
        }
        this.initialize(appRouteConfig);
    }

    private void initialize(AppRouteConfig appRouteConfig) {
        for (Map.Entry<HttpRequestType, Map<String, RequestHandler>> entry : appRouteConfig.getRoutes()) {
            for (Map.Entry<String, RequestHandler> innerEntry : entry.getValue().entrySet()) {
                List<String> params = new ArrayList<>();
                String newPattern = this.parseRoute(innerEntry.getKey(), params);
                RoutingContext routingContext = new RoutingContextImpl(innerEntry.getValue(), params);
                this.routes.get(entry.getKey()).put(newPattern, routingContext);
            }
        }
    }

    private String parseRoute(String route, List<String> params) {
        StringBuilder routeBuilder = new StringBuilder("^");
        if ("/".equals(route)) {
            routeBuilder.append(route);
            routeBuilder.append("$");
            return routeBuilder.toString();
        }
        String[] routeParts = route.split("/");
        for (int i = 0; i < routeParts.length; i++) {
            if (!routeParts[i].startsWith("{") && !routeParts[i].endsWith("}")) {
                routeBuilder.append(routeParts[i]);
                if (i != routeParts.length - 1) {
                    routeBuilder.append("/");
                }
                continue;
            }
            Pattern pattern = Pattern.compile("<\\w+>");
            Matcher matcher = pattern.matcher(routeParts[i]);
            if(!matcher.find()) {
                continue;
            }
            String group = matcher.group(0);
            String paramName = group.substring(1, group.length() - 1);
            params.add(paramName);
            routeBuilder.append(routeParts[i].substring(1, routeParts[i].length() - 1));
            if (i != routeParts.length - 1) {
                routeBuilder.append("/");
            }
        }
        routeBuilder.append("$");
        return routeBuilder.toString();
    }

    @Override
    public Map<HttpRequestType, Map<String, RoutingContext>> getRoutes() {
        return this.routes;
    }
}
