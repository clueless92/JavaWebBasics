package bg.tilchev.server;

import bg.tilchev.server.routing.AppRouteConfig;

/**
 * Created on 2017-02-12.
 */
public interface Application {

    void start(AppRouteConfig appRouteConfig);
}
