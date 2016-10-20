package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.AuthorizedUser;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.transfers.UserUtil;
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
    public UserTo get() {
        int id = AuthorizedUser.id();
        UserTo userTo = UserUtil.asTo(super.get(id));
        userTo.setPassword(null);
        return userTo;
    }
}
