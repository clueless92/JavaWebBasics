package bg.tilchev.model.dto.binding;

import java.io.Serializable;

/**
 * Created on 2017-02-18.
 */
public class AddBook implements Serializable {

    private String title;
    private String author;
    private int pages;

    public AddBook() {
        super();
    }

    public AddBook(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
