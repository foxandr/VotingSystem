package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 15.08.16.
 */
public interface VoteRepository {
    Vote save(Vote vote, int user_id);

    boolean delete(int id, int user_id);

    Vote get(int id, int user_id);

    List<Vote> getAll(int user_id);

    Vote getByDate(int user_id, LocalDate voted);

}
