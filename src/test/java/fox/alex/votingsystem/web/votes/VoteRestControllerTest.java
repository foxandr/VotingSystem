package fox.alex.votingsystem.web.votes;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.service.VoteService;
import fox.alex.votingsystem.utils.JsonUtil;
import fox.alex.votingsystem.utils.TimeUtil;
import fox.alex.votingsystem.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static fox.alex.votingsystem.TestUtil.userHttpBasic;
import static fox.alex.votingsystem.testData.UserTestData.ADMIN;
import static fox.alex.votingsystem.testData.UserTestData.ADMIN_ID;
import static fox.alex.votingsystem.testData.UserTestData.USER1;
import static fox.alex.votingsystem.testData.VoteTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fox on 30.09.16.
 */
public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + "/";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private VoteService voteService;

    @Override
    public void setUp() {
        super.setUp();
        Class clazz = TimeUtil.class;
        try {
            Field field = clazz.getDeclaredField("CHECKPOINT");
            field.setAccessible(true);
            Field modifiersField = field.getClass().getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, LocalTime.of(23, 59, 59, 99));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() throws Exception {
        int rest_id = 6;
        ResultActions actions = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(USER1))
                .param("rest_id", String.valueOf(rest_id)));
        Vote returned = MATCHER.fromJsonAction(actions);
        Vote expected = new Vote(null, rest_id);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(post(REST_URL + "admin/delete")
                .with(userHttpBasic(ADMIN))
                .param("id", String.valueOf(VOTE1.getId())))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE2, VOTE3), voteService.getAllByDate(LocalDateTime.now()));
    }

    @Test
    public void testDeleteNotAllowed() throws Exception {
        mockMvc.perform(post(REST_URL + "admin/delete")
                .with(userHttpBasic(USER1))
                .param("id", String.valueOf(VOTE2.getId())))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "get")
                .with(userHttpBasic(USER1))
                .param("id", String.valueOf(VOTE2.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(VOTE2));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + "get")
                .with(userHttpBasic(USER1))
                .param("id", "50"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "/getAll")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(VOTE2));
    }

    @Test
    public void testGetByDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DATE_TIME_FORMATTER);
        mockMvc.perform(post(REST_URL + "getByDate")
                .with(userHttpBasic(USER1))
                .param("voted", date))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(VOTE2));
    }

    @Test
    public void testUpdate() throws Exception {
        Vote updated = voteService.get(VOTE1.getId(), ADMIN_ID);
        updated.setRest_id(6);
        mockMvc.perform(put(REST_URL + VOTE1.getId())
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, voteService.get(VOTE1.getId(), ADMIN_ID));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DATE_TIME_FORMATTER);
        mockMvc.perform(get(REST_URL + "getAllByDate")
                .with(userHttpBasic(USER1))
                .param("voted", date))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(VOTE1, VOTE2, VOTE3));
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL + "get"))
                .andExpect(status().isUnauthorized());
    }

}