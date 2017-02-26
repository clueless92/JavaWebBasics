package bg.tilchev.controller;

import bg.tilchev.model.dto.binding.AddBook;
import bg.tilchev.service.BookService;
import bg.tilchev.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2017-02-19.
 */
@WebServlet("/add")
public class AddBookController extends HttpServlet {

    private final BookService bookService;

    public AddBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/templates/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String add = req.getParameter("add");
        if (add != null) {
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            int pages = Integer.parseInt(req.getParameter("pages"));
            AddBook addBook = new AddBook(title, author, pages);
            this.bookService.saveBook(addBook);
        }
        req.getRequestDispatcher("/templates/add.jsp").forward(req, resp);
    }
}
