package softuni.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50, message = "Invalid full name")
    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 3, max = 30, message = "Invalid password")
    @Column(nullable = false, length = 30)
    private String password;

    @OneToMany(mappedBy = "author")
    private Set<Article> articles;

    public User() {
        super();
    }

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getId() {
        return this.id;
    }

    public Set<Article> getArticles() {
        return Collections.unmodifiableSet(this.articles);
    }
}
