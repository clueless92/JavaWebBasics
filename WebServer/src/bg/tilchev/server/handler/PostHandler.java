package bg.tilchev.server.handler;

import bg.tilchev.server.http.context.HttpContext;
import bg.tilchev.server.http.response.HttpResponse;

import java.io.IOException;
import java.util.function.Function;

/**
 * Created on 2017-02-12.
 */
public class PostHandler extends RequestHandlerImpl {

    public PostHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {
        super.handle(httpContext);
    }
}
