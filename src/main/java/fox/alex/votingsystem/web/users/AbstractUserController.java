package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fox on 30.08.16.
 */
public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    User create(User user) {
        user.setId(null);
        log.info("create " + user);
        return service.save(user);
    };

    void delete(int id){
        log.info("delete " + id);
        service.delete(id);
    };

    User get(int id){
        log.info("get " + id);
        return service.get(id);
    };

    User getByEmail(String email){
        log.info("email " + email);
        return service.getByEmail(email);
    };

    List<User> getAll(){
        log.info("getAll users");
        return service.getAll();
    };

    void update(User user, int id){
        user.setId(id);
        log.info("update " + user);
        service.update(user);
    };

    User getWithVoices(int id){
        log.info("get with voices " + id);
        return service.getWithVoices(id);
    };
}
