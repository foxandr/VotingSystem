package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.service.UserService;
import fox.alex.votingsystem.to.UserTo;
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

    public User create(User user) {
        user.setId(null);
        log.info("create " + user);
        return service.save(user);
    };

    public void delete(int id){
        log.info("delete " + id);
        service.delete(id);
    };

    public User get(int id){
        log.info("get " + id);
        return service.get(id);
    };

    public User getByEmail(String email){
        log.info("email " + email);
        return service.getByEmail(email);
    };

    public List<User> getAll(){
        log.info("getAll users");
        return service.getAll();
    };

    public void update(User user, int id){
        user.setId(id);
        log.info("update " + user);
        service.update(user);
    };

    public void update(UserTo userTo){
        log.info("update " + userTo);
        service.update(userTo);
    };

    public User getWithVoices(int id){
        log.info("get with voices " + id);
        return service.getWithVoices(id);
    };
}
