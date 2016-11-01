package fox.alex.votingsystem.web.dishes;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.testData.RestaurantTestData;
import fox.alex.votingsystem.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static fox.alex.votingsystem.TestUtil.userHttpBasic;
import static fox.alex.votingsystem.testData.DishTestData.*;
import static fox.alex.votingsystem.testData.DishTestData.DISH3;
import static fox.alex.votingsystem.testData.DishTestData.DISH4;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST2;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID1;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID2;
import static fox.alex.votingsystem.testData.UserTestData.USER1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by fox on 01.11.16.
 */
public class DishUserRestControllerTest extends AbstractControllerTest {

    private final static String REST_URL = DishUserRestController.REST_URL + "/";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "get")
                .with(userHttpBasic(USER1))
                .param("id", String.valueOf(DISH_ID))
                .param("rest_id", String.valueOf(REST_ID1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH1));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + REST_ID1 + "/get")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH1, DISH2, DISH3, DISH4));
    }

    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(post(REST_URL + "getByDate")
                .with(userHttpBasic(USER1))
                .param("updated", LocalDate.now().toString())
                .param("rest_id", String.valueOf(REST_ID1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH1, DISH2, DISH3, DISH4));
    }

    @Test
    public void testGetWithRerstaurant() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "getWithRest")
                .with(userHttpBasic(USER1))
                .param("id", DISH5.getId().toString())
                .param("rest_id", String.valueOf(REST_ID2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH5));
        Dish returned = MATCHER.fromJsonAction(action);
        RestaurantTestData.MATCHER.assertEquals(REST2, returned.getRestaurant());
    }

}