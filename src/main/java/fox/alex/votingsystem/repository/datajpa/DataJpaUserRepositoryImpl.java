package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Override
    public User save(User user) {
        return proxyUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return proxyUserRepository.delete(id) != 0;
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
}
