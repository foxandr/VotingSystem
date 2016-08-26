package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.repository.VoteRepository;
import fox.alex.votingsystem.utils.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote save(Vote vote, int user_id) {
        return ExceptionUtil.checkVotingTime(repository.save(vote, user_id));
    }

    @Override
    public void delete(int id, int user_id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, user_id), id);
    }

    @Override
    public Vote get(int id, int user_id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, user_id), id);
    }

    @Override
    public List<Vote> getAll(int user_id) {
        return repository.getAll(user_id);
    }

    @Override
    public Vote getByDate(int user_id, LocalDateTime voted) {
        return ExceptionUtil.checkNotFound(repository.getByDate(user_id, voted), "voted=" + voted);
    }

    @Override
    public void update(Vote vote, int user_id) {
        ExceptionUtil.checkVotingTime(repository.save(vote, user_id));
    }

    @Override
    public List<Vote> getAllByDate(LocalDateTime voted) {
        return repository.getAllByDate(voted);
    }
}
