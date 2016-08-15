package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.Restaurant;

import java.util.List;

/**
 * Created by fox on 15.08.16.
 */
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getByName(String name);
}
