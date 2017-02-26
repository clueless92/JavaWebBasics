package bg.tilchev.controller;

import bg.tilchev.model.dto.binding.LoginUser;
import bg.tilchev.service.UserService;
import bg.tilchev.service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private final UserService userService;

    public RegisterController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/templates/register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String signUpText = request.getParameter("register");
        if (signUpText != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            LoginUser loginModel = new LoginUser(username, password);
            this.userService.createUser(loginModel);
            response.sendRedirect("/login");
        }
    }
}
