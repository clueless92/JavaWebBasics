package bg.tilchev.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on 2017-03-03.
 */
public class IssueAddEdit implements Serializable {

    private Long id;

    @Size(min = 5, message = "Name cannot be at least 5 symbols!")
    private String name;

    @NotNull(message = "Invalid Status!")
    private String status;

    @NotNull(message = "Invalid Priority!")
    private String priority;

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
