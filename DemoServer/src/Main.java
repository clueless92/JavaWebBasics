import softuni.app.MainApplication;
import softuni.server.Application;
import softuni.server.Server;
import softuni.server.ServerImpl;
import softuni.server.routing.AppRouteConfig;
import softuni.server.routing.AppRouteConfigImpl;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        try {
            Application application = new MainApplication();
            AppRouteConfig appRouteConfig = new AppRouteConfigImpl();
            application.start(appRouteConfig);

            ServerSocket serverSocket = new ServerSocket(180);
            Server server = new ServerImpl(serverSocket, appRouteConfig);

            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
