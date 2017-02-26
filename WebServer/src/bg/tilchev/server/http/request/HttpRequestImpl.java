package bg.tilchev.server.http.request;

import bg.tilchev.server.exception.BadRequestException;
import bg.tilchev.server.http.cookies.HttpCookie;
import bg.tilchev.server.http.cookies.HttpCookieImpl;
import bg.tilchev.server.http.session.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public class HttpRequestImpl implements HttpRequest {

    private final Map<String, String> headers;
    private final Map<String, String> urlParams;
    private final Map<String, String> queryParams;
    private final Map<String, String> formData;
    private HttpRequestType requesMethod;
    private String url;
    private String path;
    private HttpSession session;
    private HttpCookie cookie;

    public HttpRequestImpl(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestException,
            UnsupportedEncodingException {
        this.headers = new HashMap<>();
        this.urlParams = new HashMap<>();
        this.queryParams = new HashMap<>();
        this.formData = new HashMap<>();
        this.parseRequest(requestString, sessionMap);
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public HttpRequestType getRequestType() {
        return this.requesMethod;
    }

    @Override
    public String getHeader(String name) {
        return this.headers.get(name);
    }

    @Override
    public Map<String, String> getUrlParams() {
        return this.urlParams;
    }

    @Override
    public Map<String, String> getQueryParams() {
        return this.queryParams;
    }

    @Override
    public Map<String, String> getFormData() {
        return this.formData;
    }

    @Override
    public void addParam(String name, String value) {
        this.urlParams.put(name, value);
    }

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public HttpSession getSession() {
        return this.session;
    }

    @Override
    public HttpCookie getCookie() {
        return this.cookie;
    }

    @Override
    public void setSession(Map<String, HttpSession> sessionMap) {
        this.session = sessionMap.get(this.cookie.getCookie("sessionId"));
    }

    private void parseRequest(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestException,
            UnsupportedEncodingException {
        String[] requestLines = requestString.split("\n"); // TODO: could cause issues
        String[] requestLine = requestLines[0].trim().split("\\s+");
        if (requestLine.length != 3 || !requestLine[2].toLowerCase().equals("http/1.1")) {
            throw new BadRequestException("Invalid request line");
        }
        this.requesMethod = this.parseRequestMethod(requestLine[0].toLowerCase());
        this.url = requestLine[1];
        this.path = this.url.split("\\?")[0];
        this.parseHeaders(requestLines);
        this.parseParams();
        if (this.requesMethod == HttpRequestType.POST) {
            this.parseQuery(requestLines[requestLines.length - 1], this.formData);
        }
        this.parseCookie();
        this.setSession(sessionMap);
    }

    private void parseCookie() {
        String cookieStr = this.headers.get("Cookie");
        this.cookie = new HttpCookieImpl();
        if (cookieStr == null) {
            return;
        }
        String[] cookies = cookieStr.split(";\\s*");
        for (String cookie : cookies) {
            String[] cookieArgs = cookie.trim().split("=");
            this.cookie.addCookie(cookieArgs[0].trim(), cookieArgs[1].trim());
        }
    }

    private void parseParams() throws UnsupportedEncodingException {
        if (!this.url.contains("?")) {
            return;
        }
        String query = this.url.split("\\?")[1];
        this.parseQuery(query, this.queryParams);
    }

    private void parseQuery(String query, Map<String, String> map) throws UnsupportedEncodingException {
        if (!query.contains("=")) {
            return;
        }
        String[] queryPairs = query.split("&");
        for (String queryPair : queryPairs) {
            String[] queryArgs = queryPair.split("=");
            String key = URLDecoder.decode(queryArgs[0], "UTF-8");
            String value = URLDecoder.decode(queryArgs[1], "UTF-8");
            map.put(key, value);
        }
    }

    private void parseHeaders(String[] requestLines) throws BadRequestException {
        int endIndex = Arrays.asList(requestLines).indexOf("\r");
        for (int i = 1; i < endIndex; i++) {
            String[] args = requestLines[i].split(": ");
            this.headers.put(args[0], args[1]);
        }
        if (!this.headers.containsKey("Host")) {
            throw new BadRequestException("Invalid request line");
        }
    }

    private HttpRequestType parseRequestMethod(String requestMethod) throws BadRequestException {
        switch (requestMethod) {
            case "post":
                return HttpRequestType.POST;
            case "get":
                return HttpRequestType.GET;
            default:
                throw new BadRequestException("Invalid request line");
        }
    }
}
