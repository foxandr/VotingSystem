package fox.alex.votingsystem.web.restaurants;

import fox.alex.votingsystem.TestUtil;
import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.service.RestaurantService;
import fox.alex.votingsystem.testData.DishTestData;
import fox.alex.votingsystem.to.RestaurantTo;
import fox.alex.votingsystem.utils.JsonUtil;
import fox.alex.votingsystem.utils.transfers.RestaurantUtil;
import fox.alex.votingsystem.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static fox.alex.votingsystem.TestUtil.userHttpBasic;
import static fox.alex.votingsystem.testData.RestaurantTestData.*;
import static fox.alex.votingsystem.testData.UserTestData.ADMIN;
import static fox.alex.votingsystem.testData.UserTestData.USER1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fox on 07.09.16.
 */
public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public void setUp() {
        restaurantService.evictCache();
        super.setUp();
    }

    @Test
    public void testCreate() throws Exception {
        RestaurantTo expectedTo = new RestaurantTo(null, "ПагоняЎ", "Дзяржынскага 88");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expectedTo))).andExpect(status().isCreated());
        Restaurant returned = MATCHER.fromJsonAction(action);
        Restaurant expected = RestaurantUtil.createNewFromTo(expectedTo);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(REST3, REST1, expected, REST2), restaurantService.getAll());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + REST_ID1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(REST3, REST2), restaurantService.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 123)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + REST_ID1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(REST1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 123)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(REST3, REST1, REST2)));
    }

    @Test
    public void testGetByName() throws Exception {
        mockMvc.perform(get(REST_URL + "by")
                .with(userHttpBasic(ADMIN))
                .param("name", REST1.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(REST1));
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = restaurantService.get(REST_ID1);
        updated.setAddress("Василька 69");
        mockMvc.perform(put(REST_URL + REST_ID1)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, restaurantService.get(REST_ID1));
    }

    @Test
    public void testGetWithDishes() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + REST_ID1 + "/dishes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(REST1));
        Restaurant restaurant = MATCHER.fromJsonAction(action);
        DishTestData.MATCHER.assertCollectionEquals(Arrays.asList(DishTestData.DISH1, DishTestData.DISH2, DishTestData.DISH3, DishTestData.DISH4), restaurant.getDishes());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }
}