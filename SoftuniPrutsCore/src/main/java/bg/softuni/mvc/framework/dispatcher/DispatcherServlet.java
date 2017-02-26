package bg.softuni.mvc.framework.dispatcher;

import bg.softuni.mvc.framework.controller.ControllerActionPair;
import bg.softuni.mvc.framework.handlers.HandlerActionImpl;
import bg.softuni.mvc.framework.handlers.HandlerMappingImpl;
import bg.softuni.mvc.framework.interfaces.Dispatcher;
import bg.softuni.mvc.framework.interfaces.HandlerAction;
import bg.softuni.mvc.framework.interfaces.HandlerMapping;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet implements Dispatcher {

    private HandlerMapping handlerMapping;

    private HandlerAction handlerAction;

    public DispatcherServlet() {
        this.handlerMapping = new HandlerMappingImpl();
        this.handlerAction = new HandlerActionImpl();
    }

    @Override
    public ControllerActionPair dispatchRequest(HttpServletRequest request) {
        ControllerActionPair actionPair = null;
        try {
            actionPair = this.handlerMapping.findController(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actionPair;
    }

    @Override
    public String dispatchAction(HttpServletRequest request, HttpServletResponse response,
                                 ControllerActionPair controllerActionPair) {
        String view = null;
        try {
            view = this.handlerAction.executeControllerAction(request, response, controllerActionPair);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return view;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.handleRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (this.isResource(request)) {
            this.sendResourceResponse(request, response);
            return;
        }
        this.handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControllerActionPair actionPair = this.dispatchRequest(request);
        if (actionPair != null) {
            String view = this.dispatchAction(request, response, actionPair);
            try {
                if (view.startsWith("redirect:")) {
                    response.sendRedirect(view.replace("redirect:", ""));
                } else {
                    request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResourceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getRequestURI();
        String directory = URLDecoder.decode(request.getServletContext().getResource("/").getPath(), "UTF-8");
        File file = new File(directory + url);
        ///bootstrap/css/b.min.css
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            String line;
            PrintWriter printWriter = response.getWriter();
            while ((line = bfr.readLine()) != null) {
                printWriter.print(line);
            }
        }
    }

    private boolean isResource(HttpServletRequest request){
        boolean isResource = false;
        String url = request.getRequestURI();
        if(url.contains(".")){
            isResource = true;
        }
        return isResource;
    }
}