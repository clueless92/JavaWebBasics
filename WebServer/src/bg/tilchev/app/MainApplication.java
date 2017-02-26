package bg.tilchev.app;

import bg.tilchev.app.controller.HomeController;
import bg.tilchev.app.controller.UsersController;
import bg.tilchev.server.Application;
import bg.tilchev.server.handler.GetHandler;
import bg.tilchev.server.routing.AppRouteConfig;

/**
 * Created on 2017-02-12.
 */
public class MainApplication implements Application {

    @Override
    public void start(AppRouteConfig appRouteConfig) {
        HomeController homeController = new HomeController();
        String homePath = "/";
        appRouteConfig.addRoute(homePath, new GetHandler(httpContext -> {
            return homeController.index();
        }));
        UsersController usersController = new UsersController();
        String usersPath = "/users/{(?<name>[a-z]+)}/details";
        appRouteConfig.addRoute(usersPath, new GetHandler(httpContext -> {
            String name = httpContext.getHttpRequest().getUrlParams().get("name");
            return usersController.details(name);
        }));
    }
}
