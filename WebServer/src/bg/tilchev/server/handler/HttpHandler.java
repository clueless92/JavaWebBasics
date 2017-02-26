package bg.tilchev.server.handler;

import bg.tilchev.server.http.context.HttpContext;
import bg.tilchev.server.routing.RoutingContext;
import bg.tilchev.server.routing.ServerRouteConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017-02-12.
 */
public class HttpHandler implements RequestHandler {

    private Writer writer;
    private ServerRouteConfig serverRouteConfig;

    public HttpHandler(ServerRouteConfig serverRouteConfig, PrintWriter writer) {
        this.serverRouteConfig = serverRouteConfig;
        this.writer = writer;
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {
        Map<String, RoutingContext> map = this.serverRouteConfig.getRoutes().get(httpContext.getHttpRequest()
                .getRequestType());
        for (Map.Entry<String, RoutingContext> entry : map.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(httpContext.getHttpRequest().getPath());
            if(!matcher.find()) {
                continue;
            }
            for (String param : entry.getValue().getParamNames()) {
                httpContext.getHttpRequest().addParam(param, matcher.group(param));
            }
            RequestHandler handler = entry.getValue().getHandler();
            handler.setWriter(this.writer);
            handler.handle(httpContext);
            return;
        }
        throw new FileNotFoundException("Route Not Found"); // TODO: can make and replace with RouteNotFoundException
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
