package bg.tilchev.controller;

import bg.tilchev.Config;
import bg.tilchev.model.dto.binding.LoginUser;
import bg.tilchev.service.UserService;
import bg.tilchev.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final UserService userService;

    public LoginController() {
        this.userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginUser loginUser = null;
        String loginText = request.getParameter("login");
        if (loginText != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            loginUser = this.userService.findByUsernameAndPassword(username, password);
        }
        HttpSession session = request.getSession();
        session.setAttribute(Config.USER_LOGIN, loginUser);
        if (loginUser != null) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/templates/login.jsp").forward(request, response);
    }
}
