package bg.tilchev.app.view;

import bg.tilchev.server.Model;
import bg.tilchev.server.View;

/**
 * Created on 2017-02-12.
 */
public class UsersDetailsView implements View {

    private Model model;

    public UsersDetailsView(Model model) {
        this.model = model;
    }

    @Override
    public String view() {
        String name = (String) this.model.get("name");
        String html = "<html><body><h3>Welcome %s</h3></body></html>";
        return String.format(html, name);
    }
}
