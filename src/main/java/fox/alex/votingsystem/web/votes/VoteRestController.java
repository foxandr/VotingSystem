package fox.alex.votingsystem.web.votes;

import fox.alex.votingsystem.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 29.09.16.
 */
@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/votes";

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote, @RequestParam("user_id") int user_id) {
        Vote newVote = super.create(vote, user_id);
        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL + "/get").build().toUri();
        return ResponseEntity.created(uriOfNewResponse).body(newVote);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") int id, @RequestParam("user_id") int user_id) {
        super.delete(id, user_id);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Vote get(@RequestParam("id") int id, @RequestParam("user_id") int user_id) {
        return super.get(id, user_id);
    }

    @RequestMapping(path = "/{user_id}/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll(@PathVariable("user_id") int user_id) {
        return super.getAll(user_id);
    }

    @RequestMapping(path = "/getByDate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getByDate(@RequestParam("user_id") int user_id, @RequestParam("voted") LocalDateTime voted) {
        return super.getByDate(user_id, voted);
    }

    @RequestMapping(path = "/{user_id}/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Vote vote, @PathVariable("id") int id, @PathVariable("user_id") int user_id) {
        super.update(vote, id, user_id);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDate(@RequestParam("voted") LocalDateTime voted) {
        return super.getAllByDate(voted);
    }
}
