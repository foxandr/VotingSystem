package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.User;

import java.util.List;

/**
 * Created by fox on 15.08.16.
 */
public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    boolean recover(int id);

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);

    User getWithVoices(int id);
}
