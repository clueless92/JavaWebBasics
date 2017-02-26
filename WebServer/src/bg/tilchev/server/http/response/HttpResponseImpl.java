package bg.tilchev.server.http.response;

import bg.tilchev.server.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017-02-12.
 */
public abstract class HttpResponseImpl implements HttpResponse {

    private final HttpResponseCode responseCode;
    private View template;
    private Map<String, String> responseHeaders;

    protected HttpResponseImpl(String redirectUrl) {
        this.responseHeaders = new HashMap<>();
        this.responseCode = HttpResponseCode.MovedPermanently;
        this.addResponseHeader("Location", redirectUrl);
    }

    protected HttpResponseImpl(HttpResponseCode responseCode, View view) {
        this.responseHeaders = new HashMap<>();
        this.responseCode = responseCode;
        this.template = view;
    }

    @Override
    public String getResponse() {
        StringBuilder responseBuilder = new StringBuilder();
        String line = String.format("Http/1.1 %d %s%n", this.responseCode.getValue(), this.responseCode.getText());
        responseBuilder.append(line);
        for (Map.Entry<String, String> entry : this.responseHeaders.entrySet()) {
            line = String.format("%s: %s%n", entry.getKey(), entry.getValue());
            responseBuilder.append(line);
        }
        responseBuilder.append(System.lineSeparator());
        if(this.responseCode != HttpResponseCode.MovedPermanently) {
            responseBuilder.append(this.template.view());
        }
        return responseBuilder.toString();
    }

    @Override
    public void addResponseHeader(String header, String value) {
        this.responseHeaders.put(header, value);
    }
}
