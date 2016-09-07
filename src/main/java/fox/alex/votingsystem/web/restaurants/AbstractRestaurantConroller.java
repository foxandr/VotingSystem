package fox.alex.votingsystem.web.restaurants;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.service.RestaurantService;
import fox.alex.votingsystem.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fox on 07.09.16.
 */
public class AbstractRestaurantConroller {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService service;

    public Restaurant create(Restaurant restaurant){
        restaurant.setId(null);
        log.info("create " + restaurant);
        return service.save(restaurant);
    };

    public void delete(int id){
        log.info("delete " + id);
        service.delete(id);
    };

    public Restaurant get(int id){
        log.info("get " + id);
        return service.get(id);
    };

    public List<Restaurant> getAll(){
        log.info("getAll restaurants");
        return service.getAll();
    };

    public Restaurant getByName(String name) {
        log.info("getByName " + name);
        return service.getByName(name);
    };

    public void update(Restaurant restaurant, int id){
        restaurant.setId(id);
        log.info("update " + restaurant);
        service.update(restaurant);
    };

    public Restaurant getWithDishes(int id){
        log.info("getWithDishes " + id);
        return service.getWithDishes(id);
    };

    public void update(RestaurantTo restaurantTo){
        log.info("update " + restaurantTo);
        service.update(restaurantTo);
    };
}
