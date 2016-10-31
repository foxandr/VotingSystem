package fox.alex.votingsystem.web;

import fox.alex.votingsystem.AuthorizedUser;
import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.service.RestaurantService;
import fox.alex.votingsystem.service.VoteService;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.TimeUtil;
import fox.alex.votingsystem.utils.exception.WrongPasswordException;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import fox.alex.votingsystem.web.users.AbstractUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public String votingPage(ModelMap model){
        Vote currentVote = voteService.getByDate(AuthorizedUser.id(), LocalDateTime.now());
        int rest_id = 0;
        if (currentVote != null) rest_id = currentVote.getRest_id();
        boolean canVote = TimeUtil.isChangebleVote(LocalDateTime.now());
        model.addAttribute("restaurants", restaurantService.getAll());
        model.addAttribute("votingRest", rest_id);
        model.addAttribute("canVote", canVote);
        return "votingPage";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(){
        return "userList";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurantList(){
        return "restaurantList";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String dishList(ModelMap model){
        model.addAttribute("restNames", restaurantService.getAll());
        return "dishList";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(){
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String updateProfile(@Valid UserTo userTo, BindingResult bindingResult, SessionStatus sessionStatus, ModelMap model){
        if (!bindingResult.hasErrors()){
            try {
                userTo.setId(AuthorizedUser.id());
                super.update(userTo);
                AuthorizedUser.get().setUserTo(userTo);
                sessionStatus.setComplete();
                return "redirect:voting";
            } catch (DataIntegrityViolationException e){
                bindingResult.rejectValue("email", "exception.demail");
                model.addAttribute("errmail", "exception.demail");
            } catch (WrongPasswordException e){
                bindingResult.rejectValue("password", "exception.dpass");
                model.addAttribute("errpass", "exception.dpass");
            }
        }

        List<String> codes = bindingResult
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        if (codes.contains("password")) model.addAttribute("errpass", "exception.dpass");
        if (codes.contains("newpass")) model.addAttribute("errnewpass", "exception.dnewpass");
        return "profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model){
        model.addAttribute("reg", true);
        return "profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String endRegister(@Valid UserTo userTo, BindingResult bindingResult, SessionStatus sessionStatus, ModelMap model){
        if (!bindingResult.hasErrors()){
            try {
                super.create(UserUtil.createNewFromTo(userTo));
                sessionStatus.setComplete();
                return "redirect:login";
            } catch (DataIntegrityViolationException e){
                bindingResult.rejectValue("email", "exception.demail");
            }
        }
        model.addAttribute("reg", true);
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap, @RequestParam(value = "error", required = false) boolean error, @RequestParam(value = "message", required = false) String message){
        modelMap.put("error", error);
        modelMap.put("message", message);
        return "login";
    }
}
