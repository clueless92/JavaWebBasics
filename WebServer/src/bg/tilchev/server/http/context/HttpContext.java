package bg.tilchev.server.http.context;

import bg.tilchev.server.http.request.HttpRequest;

/**
 * Created on 2017-02-12.
 */
public interface HttpContext {

    HttpRequest getHttpRequest();
}
