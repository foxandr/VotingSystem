package fox.alex.votingsystem.web;

import fox.alex.votingsystem.web.users.AbstractUserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fox on 30.08.16.
 */
@Controller
public class RootController extends AbstractUserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(){
        return "redirect:voting";
    }

    @RequestMapping(value = "/voting", method = RequestMethod.GET)
    public String votingPage(){
        return "votingPage";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(){
        return "userList";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurantList(){
        return "restaurantList";
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String dishList(){
        return "dishList";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(){
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}