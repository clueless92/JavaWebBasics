package bg.tilchev.server.handler;

import bg.tilchev.server.http.context.HttpContext;
import bg.tilchev.server.http.cookies.HttpCookie;
import bg.tilchev.server.http.request.HttpRequest;
import bg.tilchev.server.http.response.HttpResponse;
import bg.tilchev.server.http.session.HttpSession;
import bg.tilchev.server.http.session.HttpSessionImpl;
import bg.tilchev.server.http.util.SessionCreator;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

/**
 * Created on 2017-02-12.
 */
public abstract class RequestHandlerImpl implements RequestHandler {

    private final Function<HttpContext, HttpResponse> function;
    private Writer writer;

    protected RequestHandlerImpl(Function<HttpContext, HttpResponse> function) {
        this.function = function;
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {
        HttpResponse response = this.function.apply(httpContext);
        this.setSession(httpContext, response);
        response.addResponseHeader("Content-Type", "text/html");
        this.writer.write(response.getResponse());
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    private void setSession(HttpContext context, HttpResponse response) {
        HttpRequest request = context.getHttpRequest();
        HttpSession session = request.getSession();
        if (session == null) {
            String sessionId = SessionCreator.getInstance().generateSessionId();
            response.addResponseHeader("Set-Cookie",
                    "sessionId=" + sessionId + "; HttpOnly; path=/");
            HttpSession newSession = new HttpSessionImpl(sessionId);
            request.setSession(newSession); // TODO: continue from 3:06:15
        }
    }
}
