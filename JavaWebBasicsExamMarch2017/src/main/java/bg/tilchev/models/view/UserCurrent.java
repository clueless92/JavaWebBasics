package bg.tilchev.models.view;

import bg.tilchev.models.enums.Role;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created on 2017-03-03.
 */
public class UserCurrent implements Serializable {

    private Long id;
    private String email;
    private String fullName;
    private String password;
    private Role role;
    private Set<Long> gameIds;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Long> getGameIds() {
        if (this.gameIds == null) {
            this.gameIds = new LinkedHashSet<>();
        }
        return this.gameIds;
    }

    public boolean containsId(Long gameId) {
        for (Long id : this.getGameIds()) {
            if (id.equals(gameId)) {
                return true;
            }
        }
        return false;
    }

    public void setGameIds(Set<Long> gameIds) {
        this.gameIds = gameIds;
    }

    public void addGameId(Long gameId) {
        this.getGameIds().add(gameId);
    }
}
