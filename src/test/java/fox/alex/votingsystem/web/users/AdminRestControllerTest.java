package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.TestUtil;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.JsonUtil;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import fox.alex.votingsystem.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static fox.alex.votingsystem.TestUtil.userHttpBasic;
import static fox.alex.votingsystem.testData.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fox on 07.09.16.
 */
public class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    public void testCreate() throws Exception {
        UserTo expected = new UserTo(null, "New", "newPass", "new@gmail.com");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());
        User returned = MATCHER.fromJsonAction(action);
        User expectedUser = UserUtil.createNewFromTo(expected);
        expectedUser.setId(returned.getId());
        MATCHER.assertEquals(expectedUser, returned);
    }

    @Test
    public void testDelete() throws Exception {
        USER1.setActive(false);
        mockMvc.perform(post(REST_URL + "delete")
                .with(userHttpBasic(ADMIN))
                .param("id", USER1.getId().toString()))
                .andExpect(status().isOk());
        MATCHER.assertEquals(USER1, userService.get(USER_ID));
    }

    @Test
    public void testRecover() throws Exception {
        userService.delete(USER_ID);
        mockMvc.perform(post(REST_URL + "recover")
                .with(userHttpBasic(ADMIN))
                .param("id", USER1.getId().toString()))
                .andExpect(status().isOk());
        MATCHER.assertEquals(USER1, userService.get(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 17)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetByEmail() throws Exception {
        mockMvc.perform(post(REST_URL + "email")
                .with(userHttpBasic(ADMIN))
                .param("email", ADMIN.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(ADMIN, USER1, USER2)));
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = userService.get(USER_ID);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, userService.get(USER_ID));
    }

    @Test
    public void testGetWithVoices() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID + "/voices")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }

}