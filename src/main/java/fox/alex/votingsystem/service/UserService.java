package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.utils.exception.NotFoundException;

import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    //TODO user transfer object
    //void update(UserTo user);

    List<User> getAll();

    void update(User user);

    void evictCache();

}
