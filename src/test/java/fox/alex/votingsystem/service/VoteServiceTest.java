package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import fox.alex.votingsystem.utils.exception.VotingException;
import org.hibernate.jdbc.Expectations;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static fox.alex.votingsystem.testData.UserTestData.ADMIN_ID;
import static fox.alex.votingsystem.testData.UserTestData.USER_ID;
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
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE2, VOTE3, newVote), service.getAllByDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)));
    }

    @Test(expected = DataAccessException.class)
    public void saveWrongData(){
        service.save(new Vote(null, 6), ADMIN_ID);
    }

    @Test(expected = VotingException.class)
    public void saveWrongTime(){
        service.save(VOTE1, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound(){
        service.delete(VOTE_ID, USER_ID);
    }

    @Test
    public void get() throws Exception {
        Vote userVote = service.get(VOTE_ID + 1, USER_ID);
        MATCHER.assertEquals(VOTE2, userVote);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        service.get(VOTE_ID, USER_ID);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Vote> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1), all);
    }

    @Test
    public void getByDate() throws Exception {
        Vote todayVote = service.getByDate(ADMIN_ID, LocalDateTime.now());
        MATCHER.assertEquals(VOTE1, todayVote);
    }

    @Test(expected = NotFoundException.class)
    public void getByDateNotFound() throws Exception {
        service.getByDate(ADMIN_ID, LocalDateTime.now().plusDays(1l));
    }

    @Test
    public void update() throws Exception {
        Vote updateVote = service.get(VOTE_ID, ADMIN_ID);
        updateVote.setRest_id(6);
        service.save(updateVote, ADMIN_ID);
        MATCHER.assertEquals(updateVote, service.get(VOTE_ID, ADMIN_ID));
    }

    @Test(expected = VotingException.class)
    public void updateWrongTime(){
        Vote updateVote = service.get(VOTE_ID, ADMIN_ID);
        updateVote.setRest_id(6);
        service.save(updateVote, ADMIN_ID);
    }

    @Test
    public void getAllByDate() throws Exception {
        Collection<Vote> all = service.getAllByDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        MATCHER.assertCollectionEquals(VOTE_LIST, all);
    }

}