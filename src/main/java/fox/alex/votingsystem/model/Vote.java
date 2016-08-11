package fox.alex.votingsystem.model;

import java.util.Date;

/**
 * Created by fox on 11.08.16.
 */

public class Vote {

    private Integer user_id;

    private Integer rest_id = 0;

    private Date voted = new Date();

    public Vote() {}

    public Vote(Integer user_id) {
        this.user_id = user_id;
    }

    public Vote(Integer user_id, Integer rest_id) {
        this.user_id = user_id;
        this.rest_id = rest_id;
    }

    public Vote (Vote vote){
        this(vote.getUser_id(), vote.getRest_id());
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRest_id() {
        return rest_id;
    }

    public void setRest_id(Integer rest_id) {
        this.rest_id = rest_id;
    }

    public Date getVoted() {
        return voted;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user_id=" + user_id +
                ", rest_id=" + rest_id +
                ", voted=" + voted +
                '}';
    }
}