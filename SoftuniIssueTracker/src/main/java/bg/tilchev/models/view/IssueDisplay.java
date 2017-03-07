package bg.tilchev.models.view;

import bg.tilchev.models.enums.Priority;
import bg.tilchev.models.enums.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017-03-03.
 */
public class IssueDisplay implements Serializable {

    private Long id;
    private String name;
    private Status status;
    private Priority priority;
    private Date createdOn;
    private String authorName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
