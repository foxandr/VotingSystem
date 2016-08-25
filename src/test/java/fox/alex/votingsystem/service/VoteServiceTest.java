package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.model.Vote;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static fox.alex.votingsystem.testData.UserTestData.ADMIN_ID;
import static fox.alex.votingsystem.testData.VoteTestData.*;

/**
 * Created by fox on 25.08.16.
 */
public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Autowired
    private UserService userService;

    @Test
    public void save() throws Exception {
        User newUser = userService.save(new User(null, "Duplicate", "user3@votes.by", "newPass", Role.ROLE_USER));
        Vote newVote = service.save(new Vote(null, 6), newUser.getId());
        //System.out.println(service.getAllByDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)));
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE2, VOTE3, newVote), service.getAllByDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        Collection<Vote> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1), all);
    }

    @Test
    public void getByDate() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getAllByDate() throws Exception {

    }

}