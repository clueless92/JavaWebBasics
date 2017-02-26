package bg.tilchev;

import bg.tilchev.model.dto.binding.AddBook;
import bg.tilchev.model.dto.binding.LoginUser;
import bg.tilchev.model.dto.view.BrowseBook;
import bg.tilchev.service.BookService;
import bg.tilchev.service.BookServiceImpl;
import bg.tilchev.service.UserService;
import bg.tilchev.service.UserServiceImpl;

/**
 * Created on 2017-02-18.
 */
public class Test {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        LoginUser loginUser = new LoginUser("Pesho", "tajna");
        userService.createUser(loginUser);
        LoginUser user = userService.findByUsernameAndPassword("Pesho", "tajna");

        BookService bookService = new BookServiceImpl();
        AddBook addBook = new AddBook("Asd", "Dsa", 5);
        bookService.saveBook(addBook);
        BrowseBook book = bookService.findBookByTitle("Asd");
    }
}
