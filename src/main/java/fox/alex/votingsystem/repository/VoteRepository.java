package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.Vote;

import java.time.LocalDateTime;

/**
 * Created by fox on 15.08.16.
 */
public interface VoteRepository {
    Vote save(Vote vote);

    boolean delete(int id);

    Vote getByUser(int user_id);

    Vote getByDate(LocalDateTime voted);

}
