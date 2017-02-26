package bg.tilchev;

import bg.tilchev.app.MainApplication;
import bg.tilchev.server.Application;
import bg.tilchev.server.Server;
import bg.tilchev.server.ServerImpl;
import bg.tilchev.server.routing.AppRouteConfig;
import bg.tilchev.server.routing.AppRouteConfigImpl;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created on 2017-02-12.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Application application = new MainApplication();
            AppRouteConfig appRouteConfig = new AppRouteConfigImpl();
            application.start(appRouteConfig);
            ServerSocket serverSocket = new ServerSocket(280);
            Server server = new ServerImpl(serverSocket, appRouteConfig);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
