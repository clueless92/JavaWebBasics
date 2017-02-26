package bg.tilchev.app.controller;

import bg.tilchev.app.view.UsersDetailsView;
import bg.tilchev.server.Model;
import bg.tilchev.server.http.response.HttpResponse;
import bg.tilchev.server.http.response.HttpResponseCode;
import bg.tilchev.server.http.response.ViewResponse;

/**
 * Created on 2017-02-12.
 */
public class UsersController {

    public HttpResponse details(String name) {
        Model model = new Model();
        model.add("name", name);
        return new ViewResponse(HttpResponseCode.OK, new UsersDetailsView(model));
    }
}
