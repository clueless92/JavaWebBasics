//package bg.tilchev.controllers;
//
//import bg.tilchev.entities.User;
//import bg.tilchev.repository.UserRepo;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/users")
//public class FormServlet extends HttpServlet {
//
//    @Inject
//    private UserRepo userRepo;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/users.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User user = new User(username, password);
//        this.userRepo.create(user);
//        response.sendRedirect("/users");
//    }
//}
