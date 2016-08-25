package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.testData.RestaurantTestData;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static fox.alex.votingsystem.testData.DishTestData.*;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID1;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID2;
import static fox.alex.votingsystem.testData.RestaurantTestData.REST_ID3;


/**
 * Created by fox on 24.08.16.
 */
public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void save() throws Exception {
        Dish newDish = service.save(new Dish(null, "Цукіні", 4.35), REST_ID2);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH5, DISH6, DISH7, DISH8, newDish), service.getAll(REST_ID2));
    }

    @Test(expected = DataAccessException.class)
    public void saveWrongData(){
        service.save(new Dish(null, "Грибное лукошко", 13.0), REST_ID1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(16, REST_ID3);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH9, DISH11), service.getAll(REST_ID3));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound(){
        service.delete(16, REST_ID2);
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(8, REST_ID1);
        MATCHER.assertEquals(DISH2, dish);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        Dish dish = service.get(8, REST_ID3);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Dish> all = service.getAll(REST_ID1);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH1, DISH2, DISH3, DISH4), all);
    }

    @Test
    public void getByDate() throws Exception {
        Collection<Dish> all = service.getByDate(REST_ID3, LocalDate.now());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH10, DISH9, DISH11), all);
    }

    @Test
    public void getWithRerstaurant() throws Exception {
        Dish dishRest2 = service.getWithRerstaurant(12, REST_ID2);
        MATCHER.assertEquals(DISH6, dishRest2);
        RestaurantTestData.MATCHER.assertEquals(RestaurantTestData.REST2, dishRest2.getRestaurant());
    }

    @Test(expected = NotFoundException.class)
    public void getWithWrongRestaurant(){
        service.getWithRerstaurant(12, REST_ID3);
    }

    @Test
    public void update() throws Exception {
        Dish updated = service.get(7, REST_ID1);
        updated.setPrice(3.0);
        service.update(updated, REST_ID1);
        MATCHER.assertEquals(updated, service.get(7, REST_ID1));
    }

}