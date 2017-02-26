package bg.tilchev.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017-02-18.
 */
public class Book implements Serializable {

    private long id;
    private String title;
    private String author;
    private int pages;
    private Date creationDate;

    public Book() {
        super();
        this.creationDate = new Date();
    }

    public Book(String title, String author, int pages) {
        this();
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
