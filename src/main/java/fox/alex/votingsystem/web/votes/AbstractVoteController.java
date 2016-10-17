package fox.alex.votingsystem.web.votes;

import fox.alex.votingsystem.AuthorizedUser;
import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.service.RestaurantService;
import fox.alex.votingsystem.service.VoteService;
import fox.alex.votingsystem.utils.VoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fox on 29.09.16.
 */
public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    @Autowired
    private RestaurantService restaurantService;

    public Vote create(Vote vote) {
        vote.setId(null);
        int user_id = AuthorizedUser.id();
        log.info("create " + vote);
        return voteService.save(vote, user_id, LocalDateTime.now());
    }

    public void delete(int id) {
        int user_id = AuthorizedUser.id();
        log.info("delete " + id);
        voteService.delete(id, user_id);
    }

    public Vote get(int id) {
        int user_id = AuthorizedUser.id();
        log.info("get " + id);
        return voteService.get(id, user_id);
    }

    public List<Vote> getAll() {
        int user_id = AuthorizedUser.id();
        log.info("createAllUserVotes");
        return voteService.getAll(user_id);
    }

    public Vote getByDate(LocalDateTime voted) {
        int user_id = AuthorizedUser.id();
        log.info("get votes of user with id=" + user_id);
        return voteService.getByDate(user_id, voted);
    }

    public void update(Vote vote, int id) {
        vote.setId(id);
        int user_id = AuthorizedUser.id();
        log.info("update " + vote);
        voteService.update(vote, user_id, LocalDateTime.now());
    }

    public List<Vote> getAllByDate(LocalDateTime voted) {
        log.info("getAllVotesBy " + voted);
        return voteService.getAllByDate(voted);
    }

    public List<List<String>> getVoteResults(){
        List<Vote> votes = getAllByDate(LocalDateTime.now());
        Map<Integer, Long> voteres = VoteUtil.getCompliteResults(votes);
        List<List<String>> result = new ArrayList<>();
        int counter = 1;
        for (Map.Entry e : voteres.entrySet()){
            List<String> temp = new ArrayList<>();
            temp.add(String.valueOf(counter));
            temp.add(restaurantService.get((Integer) e.getKey()).getName());
            temp.add(String.valueOf(e.getValue()));
            result.add(temp);
            counter++;
        }
        return result;
    }
}
