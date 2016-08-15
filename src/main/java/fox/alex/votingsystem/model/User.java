package fox.alex.votingsystem.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

/**
 * Created by fox on 11.08.16.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_idx")})
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 6)
    private String password;

    @Column(name = "registred", columnDefinition = "timestamp default now()")
    private Date registred = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("voted DESC")
    private List<Vote> votes;

    public User() {}

    public User(Integer id, String name, String email, String password, Set<Role> roles, List<Vote> votes) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoles(roles);
        setVotes(votes);
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles(), user.getVotes());
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

    public void setRegistred(Date registred) {
        this.registred = registred;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
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
                ", vote=" + votes +
                '}';
    }
}
