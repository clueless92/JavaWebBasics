package bg.tilchev.server.http.context;

import bg.tilchev.server.exception.BadRequestException;
import bg.tilchev.server.http.request.HttpRequest;
import bg.tilchev.server.http.request.HttpRequestImpl;
import bg.tilchev.server.http.session.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class HttpContextImpl implements HttpContext {

    private final HttpRequest httpRequest;

    public HttpContextImpl(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestException, UnsupportedEncodingException {
        this.httpRequest = new HttpRequestImpl(requestString, sessionMap);
    }

    @Override
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }
}
