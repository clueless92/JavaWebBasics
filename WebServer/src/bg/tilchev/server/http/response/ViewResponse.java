package bg.tilchev.server.http.response;

import bg.tilchev.server.View;

/**
 * Created on 2017-02-12.
 */
public class ViewResponse extends HttpResponseImpl {

    public ViewResponse(String redirectUrl) {
        super(redirectUrl);
    }

    public ViewResponse(HttpResponseCode responseCode, View view) {
        super(responseCode, view);
    }
}
