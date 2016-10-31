package fox.alex.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by fox on 11.08.16.
 */
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "voted"}, name = "votes_idx")})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Vote {

    @Id
    @SequenceGenerator(name = "start_index", sequenceName = "start_index", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "start_index")
    private Integer id;

    @Column(name = "rest_id", nullable = false)
    private Integer rest_id;

    @Column(name = "voted", columnDefinition = "timestamp default now()")
    private LocalDateTime voted = LocalDateTime.now();

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

    public LocalDateTime getVoted() {
        return voted;
    }

    public void setVoted(LocalDateTime voted) {
        this.voted = voted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew () {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", rest_id=" + rest_id +
                ", voted=" + voted +
                '}';
    }
}