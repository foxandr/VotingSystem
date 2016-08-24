package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.repository.JpaUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;

import static fox.alex.votingsystem.testData.UserTestData.*;
import static org.junit.Assert.*;

/**
 * Created by fox on 24.08.16.
 */
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

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
        User newUser = new User(null, "New", "new@gmail.com", "newPass", new HashSet<Role>(Arrays.asList(Role.ROLE_USER)));
        User created = service.save(newUser);
        newUser.setId(created.getId());
        System.out.println(service.getAll());
        //MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER1, USER2), service.getAll());
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void getByEmail() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void loadUserByUsername() throws Exception {
        //TODO needs to be implemented
    }

}