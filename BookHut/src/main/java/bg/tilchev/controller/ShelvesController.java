package bg.tilchev.controller;

import bg.tilchev.Config;
import bg.tilchev.model.dto.view.BrowseBook;
import bg.tilchev.service.BookService;
import bg.tilchev.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 2017-02-19.
 */
@WebServlet("/shelves")
public class ShelvesController extends HttpServlet {

    private final BookService bookService;

    public ShelvesController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<BrowseBook> books = this.bookService.getAllBooks();
        req.setAttribute(Config.BROWSE_BOOKS_LIST, books);
        req.getRequestDispatcher("/templates/shelves.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
