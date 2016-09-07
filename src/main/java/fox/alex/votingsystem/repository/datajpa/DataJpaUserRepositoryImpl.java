package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.repository.UserRepository;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Override
    public User save(User user) {
        return proxyUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        User activeUser = get(id);
        if (activeUser == null) throw new NotFoundException("No user");
        activeUser.setActive(false);
        User inactiveUser = proxyUserRepository.save(activeUser);
        return !inactiveUser.isActive();
    }

    @Override
    public boolean recover(int id) {
        User inactiveUser = get(id);
        if (inactiveUser == null) throw new NotFoundException("No user");
        inactiveUser.setActive(true);
        User activeUser = proxyUserRepository.save(inactiveUser);
        return activeUser.isActive();
    }

    @Override
    public User get(int id) {
        return proxyUserRepository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return proxyUserRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return proxyUserRepository.getByEmail(email);
    }

    @Override
    public User getWithVoices(int id) {
        return proxyUserRepository.findById(id);
    }
}
