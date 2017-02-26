package bg.tilchev.server.handler;

import bg.tilchev.server.http.context.HttpContext;

import java.io.IOException;
import java.io.Writer;

/**
 * Created on 2017-02-12.
 */
public interface RequestHandler {

    void handle(HttpContext httpContext) throws IOException;

    void setWriter(Writer writer);
}
