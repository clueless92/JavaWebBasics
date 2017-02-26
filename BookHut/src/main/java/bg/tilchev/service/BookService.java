package bg.tilchev.service;

import bg.tilchev.model.dto.binding.AddBook;
import bg.tilchev.model.dto.view.BrowseBook;

import java.util.List;

/**
 * Created on 2017-02-18.
 */
public interface BookService {

    void saveBook(AddBook addBookModel);

    List<BrowseBook> getAllBooks();

    BrowseBook findBookByTitle(String title);

    void deleteBookByTitle(String title);
}
