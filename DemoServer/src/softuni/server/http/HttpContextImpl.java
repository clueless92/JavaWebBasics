package softuni.server.http;

import softuni.server.exception.BadRequestException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class HttpContextImpl implements HttpContext {

    private final HttpRequestImpl httpRequest;

    public HttpContextImpl(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestException,
            UnsupportedEncodingException {
        this.httpRequest = new HttpRequestImpl(requestString, sessionMap);
    }

    @Override
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }
}
