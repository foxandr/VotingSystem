package fox.alex.votingsystem.web;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.service.RestaurantService;
import fox.alex.votingsystem.service.VoteService;
import fox.alex.votingsystem.utils.VoteUtil;
import fox.alex.votingsystem.utils.transfers.RestaurantUtil;
import fox.alex.votingsystem.web.users.AbstractUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 30.08.16.
 */
@Controller
public class RootController extends AbstractUserController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(){
        return "redirect:voting";
    }

    @RequestMapping(value = "/voting", method = RequestMethod.GET)
    public String votingPage(Model model){
        List<Vote> votes = voteService.getAllByDate(LocalDateTime.now());
        Vote currentVote = voteService.getByDate(1, LocalDateTime.now()); //TODO auth user
        int rest_id = 0;
        if (currentVote != null) rest_id = currentVote.getRest_id();
        model.addAttribute("votingResults", VoteUtil.getCompliteResults(votes));
        model.addAttribute("restNames", RestaurantUtil.getRestsWithIdsNames(restaurantService.getAll()));
        model.addAttribute("restaurants", restaurantService.getAll());
        model.addAttribute("votingRest", rest_id);
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
    public String dishList(Model model){
        model.addAttribute("restNames", restaurantService.getAll());
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
