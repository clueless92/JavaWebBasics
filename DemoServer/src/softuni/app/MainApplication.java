package softuni.app;

import softuni.app.controller.HomeController;
import softuni.app.controller.UsersController;
import softuni.server.Application;
import softuni.server.handler.GetHandler;
import softuni.server.routing.AppRouteConfig;

public class MainApplication implements Application{
    @Override
    public void start(AppRouteConfig appRouteConfig) {

        appRouteConfig.addRoute("/users/{(?<name>[a-z]+)}/details",
                new GetHandler((httpContext -> new HomeController().index(httpContext.getHttpRequest().getURLParameters().get("name")))));
    }
}
