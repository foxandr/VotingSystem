package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private ProxyVoteRepository proxyVoteRepository;

    @Override
    public Vote save(Vote vote) {
        return proxyVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return proxyVoteRepository.delete(id) != 0;
    }

    @Override
    public List<Vote> getByUser(int user_id) {
        return proxyVoteRepository.getByUser(user_id);
    }

    @Override
    public Vote getByDate(int user_id, LocalDateTime voted) {
        return proxyVoteRepository.getByDate(user_id, voted);
    }
}
