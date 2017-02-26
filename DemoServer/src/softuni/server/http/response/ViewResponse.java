package softuni.server.http.response;

import softuni.server.View;

public class ViewResponse extends HttpResponseImpl {
    public ViewResponse(HttpResponseCode responseCode, View view) {
        super(responseCode, view);
    }
}
