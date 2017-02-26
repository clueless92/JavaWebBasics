package softuni.server.handler;

import softuni.server.http.HttpContext;
import softuni.server.http.response.HttpResponse;

import java.util.function.Function;

public class GetHandler extends RequestHandlerImpl{
    public GetHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
