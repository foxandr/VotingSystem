package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 15.08.16.
 */
public interface VoteRepository {
    Vote save(Vote vote);

    boolean delete(int id);

    List<Vote> getByUser(int user_id);

    Vote getByDate(int user_id, LocalDateTime voted);

}
