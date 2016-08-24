package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.repository.JpaUtil;
import fox.alex.votingsystem.testData.VoteTestData;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static fox.alex.votingsystem.testData.UserTestData.*;
import static fox.alex.votingsystem.testData.VoteTestData.VOTE1;

/**
 * Created by fox on 24.08.16.
 */
public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaUtil jpaUtil;

    @After
    public void tearDown() throws Exception {
        service.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void save() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER1, USER2), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void saveWrongMail(){
        service.save(new User(null, "Duplicate", "user1@votes.by", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        USER1.setActive(false);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound(){
        service.delete(4);
    }

    @Test
    public void get() throws Exception {
        User admin = service.get(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, admin);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        service.get(200);
    }

    @Test
    public void getByEmail() throws Exception {
        User user2 = service.getByEmail("2thUser@votes.by");
        MATCHER.assertEquals(USER2, user2);
    }

    @Test
    public void getAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), all);
    }

    @Test
    public void update() throws Exception {
        User user1 = service.get(USER_ID);
        user1.setName("Updated");
        service.update(user1);
        MATCHER.assertEquals(user1, service.get(USER_ID));
    }

    @Test
    public void getWithVoices() {
        User admin = service.getWithVoices(ADMIN_ID);
        ADMIN.setVotes(new HashSet<>(Arrays.asList(VOTE1)));
        MATCHER.assertEquals(ADMIN, admin);
        VoteTestData.MATCHER.assertCollectionEquals(ADMIN.getVotes(), admin.getVotes());
    }

    @Test(expected = NotFoundException.class)
    public void getVoicesNotFound(){
        service.getWithVoices(77);
    }

    @Test
    public void loadUserByUsername() throws Exception {
        //TODO needs to be implemented
    }

}