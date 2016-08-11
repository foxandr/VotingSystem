package fox.alex.votingsystem.model;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by fox on 11.08.16.
 */
public class User extends BaseEntity {
    private String email;

    private String password;

    private Date registred;

    private Set<Role> roles;

    private Vote vote;

    public User() {}

    public User(Integer id, String name, String email, String password, Set<Role> roles, Vote vote) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoles(roles);
        setVote(vote);
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles(), user.getVote());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistred() {
        return registred;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = (vote == null) ? new Vote(this.id) : vote;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registred=" + registred +
                ", roles=" + roles +
                ", vote=" + vote +
                '}';
    }
}
