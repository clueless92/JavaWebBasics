package bg.tilchev.repos;

import bg.tilchev.model.entity.Book;

import java.util.*;

/**
 * Created on 2017-02-18.
 */
public class BookRepoImpl implements BookRepo {

    private static BookRepo repo;

    private Map<String, Book> books;

    private BookRepoImpl() {
        this.books = new HashMap<>();
    }

    public static BookRepo getInstance() {
        if (repo == null) {
            repo = new BookRepoImpl();
        }
        return repo;
    }

    @Override
    public void save(Book book) {
        this.books.put(book.getTitle(), book);
    }

    @Override
    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableCollection(this.books.values());
    }

    @Override
    public void deleteBookByTitle(String title) {
        this.books.remove(title);
    }

    @Override
    public Book findBookByTitle(String title) {
        return this.books.get(title);
    }
}
