package softuni.server.handler;

import softuni.server.http.HttpContext;
import softuni.server.http.response.HttpResponse;

import java.io.IOException;
import java.util.function.Function;

public class PostHandler extends RequestHandlerImpl {
    public PostHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
