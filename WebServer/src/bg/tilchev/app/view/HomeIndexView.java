package bg.tilchev.app.view;

import bg.tilchev.server.View;

/**
 * Created on 2017-02-12.
 */
public class HomeIndexView implements View {

    @Override
    public String view() {
        return "<html><body><h3>Home Index</h3></body></html>";
    }
}
