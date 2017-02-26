package softuni.server.routing;

import softuni.server.handler.RequestHandlerImpl;

/**
 * Created by s_she on 09.2.2017 г..
 */
public interface RoutingContext {
    RequestHandlerImpl getHandler();

    Iterable<String> getParamNames();

}
