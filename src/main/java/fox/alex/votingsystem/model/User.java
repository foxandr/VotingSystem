package fox.alex.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by fox on 11.08.16.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = User.GRAPH_WITH_ROLES, attributeNodes = @NamedAttributeNode("roles")),
        @NamedEntityGraph(name = User.GRAPH_WITH_ROLES_AND_VOTES, attributeNodes = {
                @NamedAttributeNode("roles"),
                @NamedAttributeNode("votes")
        })
})
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_idx")})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User extends BaseEntity {

    public static final String GRAPH_WITH_ROLES = "User.withRoles";
    public static final String GRAPH_WITH_ROLES_AND_VOTES = "User.withRolesAndVotes";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 6)
    private String password;

    @Column(name = "registred", columnDefinition = "timestamp default now()")
    private Date registred = new Date(Calendar.getInstance().getTimeInMillis());

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("voted DESC")
    private Set<Vote> votes;

    public User() {}

    public User(Integer id, String name, String email, String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, EnumSet.of(role, roles));
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles());
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

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    /*public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registred=" + registred +
                ", active=" + active +
                ", roles=" + roles +
                ", votes=" + votes +
                '}';
    }
}
