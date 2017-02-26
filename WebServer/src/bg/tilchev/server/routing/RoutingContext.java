package bg.tilchev.server.routing;

import bg.tilchev.server.handler.RequestHandler;

/**
 * Created on 2017-02-12.
 */
public interface RoutingContext {

    RequestHandler getHandler();

    Iterable<String> getParamNames();
}
