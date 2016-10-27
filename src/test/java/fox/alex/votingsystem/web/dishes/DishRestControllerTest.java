package fox.alex.votingsystem.web.dishes;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.service.DishService;
import fox.alex.votingsystem.testData.RestaurantTestData;
import fox.alex.votingsystem.to.DishTo;
import fox.alex.votingsystem.utils.JsonUtil;
import fox.alex.votingsystem.utils.transfers.DishUtil;
import fox.alex.votingsystem.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;

import static fox.alex.votingsystem.TestUtil.userHttpBasic;
import static fox.alex.votingsystem.testData.DishTestData.*;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST2;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID1;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID2;
import static fox.alex.votingsystem.testData.UserTestData.ADMIN;
import static fox.alex.votingsystem.testData.UserTestData.USER1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fox on 19.09.16.
 */
public class DishRestControllerTest extends AbstractControllerTest {

    private final static String REST_URL = DishRestController.REST_URL + "/";

    @Autowired
    private DishService dishService;

    @Test
    public void testCreate() throws Exception {
        DishTo expectedTo = new DishTo(null, "Cake", 14.54);
        ResultActions action = mockMvc.perform(post(REST_URL + String.valueOf(REST_ID1))
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(expectedTo)));
        Dish returned = MATCHER.fromJsonAction(action);
        Dish expected = DishUtil.createNewFromTo(expectedTo);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(post(REST_URL + "delete")
                .with(userHttpBasic(ADMIN))
                .param("id", String.valueOf(DISH_ID))
                .param("rest_id", String.valueOf(REST_ID1)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2, DISH3, DISH4), dishService.getAll(REST_ID1));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "get")
                .with(userHttpBasic(ADMIN))
                .param("id", String.valueOf(DISH_ID))
                .param("rest_id", String.valueOf(REST_ID1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + "get")
                .with(userHttpBasic(ADMIN))
                .param("id", String.valueOf(DISH_ID + 7))
                .param("rest_id", String.valueOf(REST_ID1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + REST_ID1 + "/get")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH1, DISH2, DISH3, DISH4));
    }

    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(post(REST_URL + "getByDate")
                .with(userHttpBasic(ADMIN))
                .param("updated", LocalDate.now().toString())
                .param("rest_id", String.valueOf(REST_ID1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH1, DISH2, DISH3, DISH4));
    }

    @Test
    public void testGetWithRerstaurant() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "getWithRest")
                .with(userHttpBasic(ADMIN))
                .param("id", DISH5.getId().toString())
                .param("rest_id", String.valueOf(REST_ID2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH5));
        Dish returned = MATCHER.fromJsonAction(action);
        RestaurantTestData.MATCHER.assertEquals(REST2, returned.getRestaurant());
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = dishService.get(DISH1.getId(), REST_ID1);
        updated.setPrice(33.33);
        mockMvc.perform(put(REST_URL + REST_ID1 + "/" + DISH1.getId())
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, dishService.get(DISH1.getId(), REST_ID1));
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + "get")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }

}