package softuni.controllers;

import softuni.repos.UserRepo;

import java.io.IOException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Stateless
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserRepo userRepo;

    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        req.setAttribute("view", "user/login.jsp");
        req.getRequestDispatcher("base-layout.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isExisting = this.userRepo.exists(req.getParameter("email"));
        System.out.println(isExisting);
    }
}
