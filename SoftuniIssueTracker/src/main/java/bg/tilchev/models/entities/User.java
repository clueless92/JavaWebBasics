package bg.tilchev.models.entities;

import bg.tilchev.models.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created on 2017-03-02.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Basic
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private Set<Issue> issues;

    public User() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Issue> getIssues() {
        return this.issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
