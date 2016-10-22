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
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote) {
        Vote newVote = super.create(vote);
        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL + "/get").build().toUri();
        return ResponseEntity.created(uriOfNewResponse).body(newVote);
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

    @RequestMapping(path = "/getByDate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getByDate(@RequestParam("voted") LocalDateTime voted) {
        return super.getByDate(voted);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Vote vote, @PathVariable("id") int id) {
        super.update(vote, id);
    }

    @RequestMapping(path = "/getAllByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDate(@RequestParam("voted") LocalDateTime voted) {
        return super.getAllByDate(voted);
    }

    @RequestMapping(path = "/getVoteResults", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<List<String>> getVoteResults() {
        return super.getVoteResults();
    }
}
