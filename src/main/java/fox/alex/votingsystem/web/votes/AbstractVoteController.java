package fox.alex.votingsystem.web.votes;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 29.09.16.
 */
public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote create(Vote vote, int user_id) {
        vote.setId(null);
        log.info("create " + vote);
        return service.save(vote, user_id, LocalDateTime.now());
    }

    public void delete(int id, int user_id) {
        log.info("delete " + id);
        service.delete(id, user_id);
    }

    public Vote get(int id, int user_id) {
        log.info("get " + id);
        return service.get(id, user_id);
    }

    public List<Vote> getAll(int user_id) {
        log.info("createAllUserVotes");
        return service.getAll(user_id);
    }

    public Vote getByDate(int user_id, LocalDateTime voted) {
        log.info("get votes of user with id=" + user_id);
        return service.getByDate(user_id, voted);
    }

    public void update(Vote vote, int id, int user_id) {
        vote.setId(id);
        log.info("update " + vote);
        service.update(vote, user_id, LocalDateTime.now());
    }

    public List<Vote> getAllByDate(LocalDateTime voted) {
        log.info("getAllVotesBy " + voted);
        return service.getAllByDate(voted);
    }
}
