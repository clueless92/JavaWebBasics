package softuni.server;

import softuni.server.http.HttpSession;
import softuni.server.routing.AppRouteConfig;
import softuni.server.routing.ServerRouteConfigImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class ServerImpl implements Server {

    private final ServerRouteConfigImpl serverRouteConfig;
    private boolean isRunning;
    private final ServerSocket serverSocket;
    private Map<String, HttpSession> sessionMap;

    public ServerImpl(ServerSocket serverSocket, AppRouteConfig appRouteConfig) {
        this.sessionMap = new HashMap<>();
        this.serverSocket = serverSocket;
        this.serverRouteConfig = new ServerRouteConfigImpl(appRouteConfig);
    }

    @Override
    public void runServer() throws SocketException {
        System.out.println("Server started");

        this.isRunning = true;
        this.serverSocket.setSoTimeout(10000);
        this.acceptRequest();
    }

    private void acceptRequest() {
        while (isRunning) {
            try (Socket clientSocket = serverSocket.accept()) {
                clientSocket.setSoTimeout(10000);
                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, this.serverRouteConfig,
                        this.sessionMap);
                FutureTask<?> futureTask = new FutureTask<>(connectionHandler, null);
                futureTask.run();
            } catch (IOException ignored) {

            }
        }
    }
}
