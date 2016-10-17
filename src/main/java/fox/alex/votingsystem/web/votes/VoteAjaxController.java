package fox.alex.votingsystem.web.votes;

import fox.alex.votingsystem.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 30.09.16.
 */
@RestController
@RequestMapping("/ajax/votes")
public class VoteAjaxController extends AbstractVoteController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@RequestParam("rest_id") int rest_id) {
        Vote vote = getByDate(LocalDateTime.now());
        if (vote == null) vote = new Vote();
        vote.setRest_id(rest_id);
        try {
            if (vote.isNew()) {
                super.create(vote);
            } else {
                super.update(vote, vote.getId());
            }
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(messageSource.getMessage("exception.dvotes", null, LocaleContextHolder.getLocale()));
        }
    }

    @RequestMapping(path = "/admin/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") int id) {
        super.delete(id);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@RequestParam("id") int id) {
        return super.get(id);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        return super.getAll();
    }

    @RequestMapping(path = "/getByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getByDate(@RequestParam("voted") LocalDateTime voted) {
        return super.getByDate(voted);
    }

    @RequestMapping(path = "/getAllByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDate(@RequestParam("voted") LocalDateTime voted) {
        return super.getAllByDate(voted);
    }

    @RequestMapping(path = "/getVoteResults", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<List<String>> getVoteResults() {
        return super.getVoteResults();
    }
}
