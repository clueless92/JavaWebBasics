package bg.softuni.mvc.framework.interfaces;

import bg.softuni.mvc.framework.controller.ControllerActionPair;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created on 2017-02-21.
 */
public interface HandlerMapping {

    ControllerActionPair findController(HttpServletRequest request)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;
}
