package bg.tilchev.controller;

import bg.tilchev.Config;
import bg.tilchev.model.dto.binding.AddBook;
import bg.tilchev.model.dto.view.BrowseBook;
import bg.tilchev.service.BookService;
import bg.tilchev.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created on 2017-02-19.
 */
@WebServlet("/shelves/edit/*")
public class EditBookController extends HttpServlet {

    private final BookService bookService;

    public EditBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String tokens[] = req.getRequestURI().split("/");
        String title = URLDecoder.decode(tokens[3], "UTF-8");
        BrowseBook browseBook = this.bookService.findBookByTitle(title);
        if (browseBook != null) {
            req.setAttribute(Config.BOOK, browseBook);
            req.getRequestDispatcher("/templates/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String edit = req.getParameter("edit");
        if (edit != null) {
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            int pages = Integer.valueOf(req.getParameter("pages"));
            AddBook browseBook = new AddBook(title, author, pages);
            this.bookService.saveBook(browseBook);
            resp.sendRedirect("/shelves");
        }
    }
}
