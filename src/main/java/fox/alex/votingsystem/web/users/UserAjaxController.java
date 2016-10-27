package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.AuthorizedUser;
import fox.alex.votingsystem.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fox on 19.10.16.
 */
@RestController
@RequestMapping("/ajax/profile")
public class UserAjaxController extends AbstractUserController {

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        int id = AuthorizedUser.id();
        User user = super.get(id);
        user.setPassword(null);
        return user;
    }
}
