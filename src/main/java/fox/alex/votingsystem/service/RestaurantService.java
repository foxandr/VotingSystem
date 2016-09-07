package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.to.RestaurantTo;
import fox.alex.votingsystem.utils.exception.NotFoundException;

import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    Restaurant getByName(String name) throws NotFoundException;

    void update(Restaurant restaurant);

    void evictCache();

    Restaurant getWithDishes(int id) throws NotFoundException;

    void update(RestaurantTo restaurantTo);

}
