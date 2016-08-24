package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private ProxyVoteRepository proxyVoteRepository;

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Override
    public Vote save(Vote vote, int user_id) {
        if (!vote.isNew() && get(vote.getId(), user_id) == null){
            return null;
        }
        vote.setUser(proxyUserRepository.findOne(user_id));
        return proxyVoteRepository.save(vote);
    }

    @Override
    public Vote get(int id, int user_id) {
        return proxyVoteRepository.findOne(id, user_id);
    }

    @Override
    public boolean delete(int id, int user_id) {
        return proxyVoteRepository.delete(id, user_id) != 0;
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
