package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.TestUtil;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.JsonUtil;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import fox.alex.votingsystem.web.AbstractControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

import static fox.alex.votingsystem.TestUtil.userHttpBasic;
import static fox.alex.votingsystem.testData.UserTestData.MATCHER;
import static fox.alex.votingsystem.testData.UserTestData.USER1;
import static fox.alex.votingsystem.testData.UserTestData.USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fox on 17.10.16.
 */
public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestController.REST_URL + "/";

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk());
        Assert.assertFalse(userService.get(USER1.getId()).isActive());
    }

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER1)));
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdate() throws Exception {
        User expected = userService.get(USER_ID);
        UserTo userTo = new UserTo(null, "UserUPD", "simpleuser", "user1@votes.by");
        mockMvc.perform(put(REST_URL)
                .with(userHttpBasic(USER1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(userTo)))
                .andDo(print())
                .andExpect(status().isOk());
        UserUtil.updateFromTo(expected, userTo);
        MATCHER.assertEquals(expected, userService.get(expected.getId()));
    }

}