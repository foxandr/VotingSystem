package fox.alex.votingsystem.model;

import java.time.LocalDateTime;

/**
 * Created by fox on 11.08.16.
 */

public class Vote {

    private Integer rest_id;

    private LocalDateTime voted;

    private User user;

    public Vote() {}

    public Vote(Integer rest_id) {
        this.rest_id = rest_id;
    }

    public Vote (Vote vote){
        this(vote.getRest_id());
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

    @Override
    public String toString() {
        return "Vote{" +
                "user_id=" + rest_id +
                ", voted=" + voted +
                '}';
    }
}