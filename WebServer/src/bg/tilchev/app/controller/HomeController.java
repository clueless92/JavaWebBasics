package bg.tilchev.app.controller;

import bg.tilchev.app.view.HomeIndexView;
import bg.tilchev.server.http.response.HttpResponse;
import bg.tilchev.server.http.response.HttpResponseCode;
import bg.tilchev.server.http.response.ViewResponse;

/**
 * Created on 2017-02-12.
 */
public class HomeController {

    public HttpResponse index() {
        return new ViewResponse(HttpResponseCode.OK, new HomeIndexView());
    }
}
