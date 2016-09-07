package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.repository.UserRepository;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.exception.ExceptionUtil;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by fox on 21.08.16.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        return repository.save(UserUtil.prepareToSave(user));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void recover(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.recover(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Objects.requireNonNull(email, "Email must not be empty");
        return ExceptionUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        UserUtil.updateFromTo(user, userTo);
        repository.save(UserUtil.prepareToSave(user));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }

    @Override
    public User getWithVoices(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithVoices(id), id);
    }

    //TODO implement security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
