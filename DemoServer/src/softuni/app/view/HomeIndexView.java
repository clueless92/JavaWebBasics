package softuni.app.view;

import softuni.server.Model;
import softuni.server.View;

/**
 * Created by s_she on 09.2.2017 г..
 */
public class HomeIndexView implements View {

    private final Model model;

    public HomeIndexView(Model model) {
        this.model = model;
    }

    @Override
    public String view() {
        return String.format("<html><body><h3>welcome %s</h3></body></html>", (String) model.get("name"));
    }
}
