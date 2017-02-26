package softuni.app.controller;

import softuni.app.view.HomeIndexView;
import softuni.server.Model;
import softuni.server.http.response.HttpResponse;
import softuni.server.http.response.HttpResponseCode;
import softuni.server.http.response.ViewResponse;

public class HomeController {

    public HttpResponse index(String name){
        Model model = new Model();
        model.add("name", name);

        return new ViewResponse(HttpResponseCode.OK, new HomeIndexView(model));
    }
}
