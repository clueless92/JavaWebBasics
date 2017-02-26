package softuni.server.routing;

import softuni.server.handler.RequestHandlerImpl;
import softuni.server.http.HttpRequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class ServerRouteConfigImpl implements ServerRouteConfig {

    private final Map<HttpRequestMethod, Map<String, RoutingContext>> routes;

    public ServerRouteConfigImpl(AppRouteConfig appRouteConfig) {
        this.routes = new HashMap<>();

        for (HttpRequestMethod httpRequestMethod : HttpRequestMethod.values()) {
            this.routes.put(httpRequestMethod, new HashMap<>());
        }

        this.initializeServerConfig(appRouteConfig);
    }

    private void initializeServerConfig(AppRouteConfig appRouteConfig) {
        for (Map.Entry<HttpRequestMethod, Map<String, RequestHandlerImpl>> entry : appRouteConfig.getRoutes()) {
            for (Map.Entry<String, RequestHandlerImpl> innerEntry : entry.getValue().entrySet()) {

                List<String> params = new ArrayList<>();
                String newPattern = this.parseRoute(innerEntry.getKey(), params);

                RoutingContext routingContext = new RoutingContextImpl(innerEntry.getValue(), params);

                this.routes.get(entry.getKey()).put(newPattern, routingContext);
            }
        }
    }

    private String parseRoute(String key, List<String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^");

        if("/".equals(key)){
            stringBuilder.append(key);
            stringBuilder.append("$");
            return stringBuilder.toString();
        }

        String[] tokens = key.split("/");

        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].startsWith("{") && !tokens[i].endsWith("}")) {
                stringBuilder.append(tokens[i]);
                stringBuilder.append(i == tokens.length - 1 ? "$" : "/");
                continue;
            }

            Pattern pattern = Pattern.compile("<\\w+>");
            Matcher matcher = pattern.matcher(tokens[i]);

            if (!matcher.find()) {
                continue;
            }

            String paramName = matcher.group(0).substring(1, matcher.group(0).length() - 1);
            params.add(paramName);
            stringBuilder.append(tokens[i].substring(1, tokens[i].length() - 1));
            stringBuilder.append(i == tokens.length - 1 ? "$" : "/");
        }
        return stringBuilder.toString();
    }

    @Override
    public Map<HttpRequestMethod, Map<String, RoutingContext>> getRoutes() {
        return this.routes;
    }
}
