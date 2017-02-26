package bg.tilchev.server.handler;

import bg.tilchev.server.http.context.HttpContext;
import bg.tilchev.server.http.response.HttpResponse;

import java.util.function.Function;

/**
 * Created on 2017-02-12.
 */
public class GetHandler extends RequestHandlerImpl {

    public GetHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
