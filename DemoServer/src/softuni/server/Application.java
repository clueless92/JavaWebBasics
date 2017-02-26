package softuni.server;

import softuni.server.routing.AppRouteConfig;

public interface Application {
    void start(AppRouteConfig appRouteConfig);
}
