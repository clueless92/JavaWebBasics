package bg.softuni.mvc.framework.interfaces;

import bg.softuni.mvc.framework.controller.ControllerActionPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2017-02-21.
 */
public interface Dispatcher {

    ControllerActionPair dispatchRequest(HttpServletRequest request);

    String dispatchAction(HttpServletRequest request, HttpServletResponse response,
                          ControllerActionPair controllerActionPair);
}
