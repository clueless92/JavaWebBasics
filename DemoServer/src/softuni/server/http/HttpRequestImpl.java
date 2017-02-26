package softuni.server.http;


import softuni.server.exception.BadRequestException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {
    private final Map<String, String> headers;
    private final Map<String, String> urlParameters;
    private final Map<String, String> queryParameters;
    private final Map<String, String> formData;
    private HttpRequestMethod requestMehtod;
    private String url;
    private String path;
    private HttpSession session;
    private HttpCookie cookie;

    public HttpRequestImpl(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestException, UnsupportedEncodingException {
        this.headers = new HashMap<>();
        this.urlParameters = new HashMap<>();
        this.queryParameters = new HashMap<>();
        this.formData = new HashMap<>();

        this.parseRequest(requestString, sessionMap);
    }

    private void parseRequest(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestException, UnsupportedEncodingException {
        String[] requestLines = requestString.split("\n");
        String[] requestLine = requestLines[0].trim().split("\\s+");

        if (requestLine.length != 3 || !requestLine[2].toLowerCase().equals("http/1.1")) {
            throw new BadRequestException("Invalid request line");
        }

        this.requestMehtod = this.parseRequestMethod(requestLine[0].toLowerCase());
        this.url = requestLine[1];
        this.path = this.url.split("\\?")[0];

        this.parseHeaders(requestLines);
        this.parseParameters();

        if(this.requestMehtod == HttpRequestMethod.POST){
            this.parseQuery(requestLines[requestLines.length - 1], this.formData);
        }

        this.parseCookie();
        this.setSession(sessionMap);
    }

    private void setSession(Map<String, HttpSession> sessionMap) {
        this.session = sessionMap.get(this.cookie.getCookie("sessionId"));
    }

    private void parseCookie() {
        String cookieString = this.headers.get("Cookie");
        this.cookie = new HttpCookieImpl();
        if(cookieString == null){
            return;
        }

        String[] cookies = cookieString.split(";\\s*");

        for (String s : cookies) {
            if(!s.contains("=")){
                continue;
            }
            String[] cookieArgs = s.trim().split("=");
            this.cookie.addCookie(cookieArgs[0].trim(), cookieArgs[1].trim());
        }
    }

    private void parseParameters() throws UnsupportedEncodingException {
        if(!this.url.contains("?")){
            return;
        }
        String query = this.url.split("\\?")[1];
        this.parseQuery(query, this.queryParameters);
    }

    private void parseQuery(String query, Map<String, String> map) throws UnsupportedEncodingException {
        if(!query.contains("=")){
            return;
        }
        String[] queryPairs = query.split("&");
        for (String queryPair : queryPairs) {
            String[] queryArgs = queryPair.split("=");
            map.put(URLDecoder.decode(queryArgs[0], "UTF-8"),
                    URLDecoder.decode(queryArgs[1], "UTF-8"));
        }
    }

    private void parseHeaders(String[] requestLines) throws BadRequestException {
        int endIndex = Arrays.asList(requestLines).indexOf("\r");
        for (int i = 1; i < endIndex; i++) {
            String[] headerArgs = requestLines[i].split(": ");
            this.headers.put(headerArgs[0], headerArgs[1]);
        }

        if(!this.headers.containsKey("Host")){
            throw new BadRequestException("Invalid headers");
        }
    }

    private HttpRequestMethod parseRequestMethod(String requestMethod) throws BadRequestException {
        switch (requestMethod) {
            case "post":
                return HttpRequestMethod.POST;
            case "get":
                return HttpRequestMethod.GET;
            default:
                throw new BadRequestException("Invalid request Line");

        }
    }

    @Override
    public String getURL() {
        return this.url;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public HttpRequestMethod getRequestType() {
        return this.requestMehtod;
    }

    @Override
    public String getHeader(String name) {
        return this.headers.get(name);
    }

    @Override
    public Map<String, String> getURLParameters() {
        return this.urlParameters;
    }

    @Override
    public Map<String, String> getQueryParameters() {
        return this.queryParameters;
    }

    @Override
    public Map<String, String> getFormData() {
        return this.formData;
    }

    @Override
    public void addParameter(String name, String value) {
        this.urlParameters.put(name, value);
    }

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public HttpSession getHttpSession() {
        return this.session;
    }

    @Override
    public HttpCookie getHttpCookie() {
        return this.cookie;
    }
}
