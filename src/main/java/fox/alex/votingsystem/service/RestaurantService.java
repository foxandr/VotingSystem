package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Restaurant;

import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getByName(String name);

    void update(Restaurant restaurant);

    void evictCache();

    Restaurant getWithDishes(int id);

    //TODO restaurant transfer object
    //void update(RestaurantTo restaurant);

}
