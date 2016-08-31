package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fox on 31.08.16.
 */
@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {

    static final String REST_URL = "/rest/users";

    @Override
    User create(User user) {
        return super.create(user);
    }

    @Override
    void delete(int id) {
        super.delete(id);
    }

    @Override
    User get(int id) {
        return super.get(id);
    }

    @Override
    User getByEmail(String email) {
        return super.getByEmail(email);
    }

    @Override
    List<User> getAll() {
        return super.getAll();
    }

    @Override
    void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    User getWithVoices(int id) {
        return super.getWithVoices(id);
    }
}
