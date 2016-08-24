package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public interface VoteService {

    Vote save(Vote vote, int user_id);

    void delete(int id, int user_id);

    Vote get(int id, int user_id);

    List<Vote> getByUser(int user_id);

    Vote getByDate(int user_id, LocalDateTime voted);

    void update(Vote vote, int user_id);
}
