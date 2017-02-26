package bg.tilchev.server;

import bg.tilchev.server.http.session.HttpSession;
import bg.tilchev.server.http.util.Constants;
import bg.tilchev.server.routing.AppRouteConfig;
import bg.tilchev.server.routing.ServerRouteConfig;
import bg.tilchev.server.routing.ServerRouteConfigImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * Created on 2017-02-12.
 */
public class ServerImpl implements Server {

    private final ServerRouteConfig serverRouteConfig;
    private boolean isRunning;
    private final ServerSocket servetSocket;
    private Map<String, HttpSession> sessionMap;

    public ServerImpl(ServerSocket serverSocket, AppRouteConfig appRouteConfig) {
        this.servetSocket = serverSocket;
        this.serverRouteConfig = new ServerRouteConfigImpl(appRouteConfig);
        this.sessionMap = new HashMap<>();
    }

    @Override
    public void run() throws SocketException {
        System.out.println("Server started!");
        this.isRunning = true;
        this.servetSocket.setSoTimeout(Constants.SO_TIMEOUT);
        this.acceptRequest();
    }

    private void acceptRequest() {
        while (this.isRunning) {
            try (Socket clientSocket = this.servetSocket.accept()) {
                clientSocket.setSoTimeout(Constants.SO_TIMEOUT);
                ConnectionHandler connectionHandler =
                        new ConnectionHandler(clientSocket, this.serverRouteConfig, this.sessionMap);
                FutureTask<?> futureTask = new FutureTask<>(connectionHandler, null);
                futureTask.run();
            } catch (IOException ignored) {
                // TODO: ignored because Simo says it can't be avoided
//                ignored.printStackTrace();
            }
        }
    }
}
