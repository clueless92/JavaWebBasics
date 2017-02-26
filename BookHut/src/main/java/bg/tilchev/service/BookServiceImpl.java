package bg.tilchev.service;

import bg.tilchev.model.dto.binding.AddBook;
import bg.tilchev.model.dto.view.BrowseBook;
import bg.tilchev.model.entity.Book;
import bg.tilchev.repos.BookRepo;
import bg.tilchev.repos.BookRepoImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created on 2017-02-18.
 */
public class BookServiceImpl implements BookService {

    private BookRepo repo;
    private ModelMapper mapper;

    public BookServiceImpl() {
        this.repo = BookRepoImpl.getInstance();
        this.mapper = new ModelMapper();
    }

    @Override
    public void saveBook(AddBook addBookModel) {
        Book book = this.mapper.map(addBookModel, Book.class);
        this.repo.save(book);
    }

    @Override
    public List<BrowseBook> getAllBooks() {
        Collection<Book> books = this.repo.getAllBooks();
        List<BrowseBook> browseBooks = new ArrayList<>();
        for (Book book : books) {
            BrowseBook browseBook = this.mapper.map(book, BrowseBook.class);
            browseBooks.add(browseBook);
        }
        return browseBooks;
    }

    @Override
    public BrowseBook findBookByTitle(String title) {
        Book book = this.repo.findBookByTitle(title);
        return this.mapper.map(book, BrowseBook.class);
    }

    @Override
    public void deleteBookByTitle(String title) {
        this.repo.deleteBookByTitle(title);
    }
}
