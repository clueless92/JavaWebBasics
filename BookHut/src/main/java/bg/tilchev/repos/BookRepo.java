package bg.tilchev.repos;

import bg.tilchev.model.entity.Book;

import java.util.Collection;

/**
 * Created on 2017-02-18.
 */
public interface BookRepo {

    void save(Book book);

    Collection<Book> getAllBooks();

    void deleteBookByTitle(String title);

    Book findBookByTitle(String title);
}
