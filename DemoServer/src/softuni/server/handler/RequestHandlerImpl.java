package softuni.server.handler;

import softuni.server.http.HttpContext;
import softuni.server.http.HttpSession;
import softuni.server.http.HttpSessionImpl;
import softuni.server.http.response.HttpResponse;
import softuni.server.util.SessionCreator;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public abstract class RequestHandlerImpl implements RequestHandler {

    private final Function<HttpContext, HttpResponse> function;
    private Writer writer;

    RequestHandlerImpl(Function<HttpContext, HttpResponse> function) {
        this.function = function;
    }

    private void setSession(HttpContext httpContext, HttpResponse httpResponse) {


        if (!httpContext.getHttpRequest().getHttpCookie().contains("sessionId") || httpContext.getHttpRequest()
                .getHttpSession() == null) {
            String sessionId = SessionCreator.getInstance().generateSessionId();

            httpResponse.addResponseHeader("Set-Cookie",
                    "sessionId=" + sessionId + "; HttpOnly; path=/");

            HttpSession httpSession = new HttpSessionImpl(sessionId);
            httpContext.getHttpRequest().setSession(httpSession);
        }
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {
        HttpResponse httpResponse = function.apply(httpContext);

        this.setSession(httpContext, httpResponse);

        httpResponse.addResponseHeader("Content-Type", "text/html");
        this.writer.write(httpResponse.getResponse());
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
