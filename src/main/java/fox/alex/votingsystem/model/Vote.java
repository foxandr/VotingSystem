package fox.alex.votingsystem.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by fox on 11.08.16.
 */
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "voted"}, name = "votes_idx")})
public class Vote {

    @Id
    @SequenceGenerator(name = "start_index", sequenceName = "start_index", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "start_index")
    private Integer id;

    @Column(name = "rest_id", nullable = false)
    @NotEmpty
    private Integer rest_id;

    @Column(name = "voted", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate voted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Vote() {}

    public Vote(Integer id, Integer rest_id) {
        this.id = id;
        this.rest_id = rest_id;
    }

    public Vote (Vote vote){
        this(vote.getId(), vote.getRest_id());
    }

    public Integer getRest_id() {
        return rest_id;
    }

    public void setRest_id(Integer rest_id) {
        this.rest_id = rest_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getVoted() {
        return voted;
    }

    public void setVoted(LocalDate voted) {
        this.voted = voted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user_id=" + rest_id +
                ", voted=" + voted +
                '}';
    }
}