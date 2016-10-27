package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.AuthorizedUser;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by fox on 17.10.16.
 */

@RestController
@RequestMapping(UserRestController.REST_URL)
public class UserRestController extends AbstractUserController {

    static final String REST_URL = "/rest/profile";

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        User user = super.get(AuthorizedUser.id());
        user.setPassword(null);
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo) {
        userTo.setId(AuthorizedUser.id());
        super.update(userTo);
    }
}
