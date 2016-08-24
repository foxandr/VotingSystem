package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.repository.JpaUtil;
import fox.alex.votingsystem.testData.DishTestData;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collection;

import static fox.alex.votingsystem.testData.DishTestData.DISH10;
import static fox.alex.votingsystem.testData.DishTestData.DISH11;
import static fox.alex.votingsystem.testData.DishTestData.DISH9;
import static fox.alex.votingsystem.testData.RestaurantTestData.*;

/**
 * Created by fox on 24.08.16.
 */
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

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
        Restaurant newrest = service.save(new Restaurant(null, "ПагоняЎ", "Дзяржынскага 88"));
        MATCHER.assertCollectionEquals(Arrays.asList(REST3, REST1, newrest, REST2), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void saveWrongData() {
        service.save(new Restaurant(null, "Семашко", "Антонова 10"));
    }

    @Test
    public void delete() throws Exception {
        service.delete(REST_ID1);
        MATCHER.assertCollectionEquals(Arrays.asList(REST3, REST2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(26);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(REST_ID3);
        MATCHER.assertEquals(REST3, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(50);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Restaurant> all = service.getAll();
        MATCHER.assertCollectionEquals(RESTAURANT_LIST, all);
    }

    @Test
    public void getByName() throws Exception {
        Restaurant restaurant = service.getByName("Семашко");
        MATCHER.assertEquals(REST2, restaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = service.get(REST_ID1);
        updated.setAddress("Лососно 14-3");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(REST_ID1));
    }

    @Test
    public void getWithDishes() throws Exception {
        Restaurant restaurant = service.getWithDishes(REST_ID3);
        REST3.setDishes(Arrays.asList(DISH10, DISH9, DISH11));
        MATCHER.assertEquals(REST3, restaurant);
        System.out.println(restaurant.getDishes());
        System.out.println(REST3.getDishes());
        DishTestData.MATCHER.assertCollectionEquals(REST3.getDishes(), restaurant.getDishes());
    }

    @Test(expected = NotFoundException.class)
    public void getWithDishesNotFound() {

    }
}