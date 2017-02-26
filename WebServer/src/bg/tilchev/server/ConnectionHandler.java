package bg.tilchev.server;

import bg.tilchev.server.exception.BadRequestException;
import bg.tilchev.server.handler.HttpHandler;
import bg.tilchev.server.http.context.HttpContext;
import bg.tilchev.server.http.context.HttpContextImpl;
import bg.tilchev.server.http.session.HttpSession;
import bg.tilchev.server.routing.ServerRouteConfig;

import java.io.*;
import java.net.Socket;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class ConnectionHandler implements Runnable {

    private final Socket clientSocket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final ServerRouteConfig serverRouteConfig;
    private Map<String, HttpSession> sessionMap;

    public ConnectionHandler(Socket clientSocket, ServerRouteConfig serverRouteConfig) throws IOException {
        this.clientSocket = clientSocket;
        this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.writer = new PrintWriter(this.clientSocket.getOutputStream());
        this.serverRouteConfig = serverRouteConfig;
    }

    public ConnectionHandler(Socket clientSocket, ServerRouteConfig serverRouteConfig,
                             Map<String, HttpSession> sessionMap) throws IOException {
        this(clientSocket, serverRouteConfig);
        this.sessionMap = sessionMap;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        StringBuilder queryBuilder = new StringBuilder();
        try {
            while (this.reader.ready() || queryBuilder.length() == 0) {
                queryBuilder.append((char) this.reader.read());
            }
            try {
                HttpContext httpContext = new HttpContextImpl(queryBuilder.toString(), this.sessionMap);
                HttpHandler handler = new HttpHandler(this.serverRouteConfig, this.writer);
                handler.handle(httpContext);
                HttpSession session = httpContext.getHttpRequest().getSession();
                this.sessionMap.put(session.getId(), session);
            } catch (BadRequestException e) {
                this.writer.write("HTTP/1.1 400 Bad Request");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                this.writer.write("HTTP/1.1 404 Not Found");
            } catch (Exception e) {
                e.printStackTrace();
                this.writer.write("HTTP/1.1 500 Internal Server Error");
            }
            this.writer.close();
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
