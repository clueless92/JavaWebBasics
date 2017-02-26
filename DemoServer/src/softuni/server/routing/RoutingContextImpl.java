package softuni.server.routing;

import softuni.server.handler.RequestHandler;
import softuni.server.handler.RequestHandlerImpl;

import java.util.List;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class RoutingContextImpl implements RoutingContext {

    private RequestHandlerImpl handler;
    private List<String> paramNames;

    public RoutingContextImpl(RequestHandlerImpl handler, List<String> paramNames) {
        this.handler = handler;
        this.paramNames = paramNames;
    }

    @Override
    public RequestHandlerImpl getHandler() {
        return this.handler;
    }

    @Override
    public Iterable<String> getParamNames() {
        return this.paramNames;
    }
}
