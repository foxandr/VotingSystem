package fox.alex.votingsystem.utils.transfers;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.to.RestaurantTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fox on 07.09.16.
 */
public class RestaurantUtil {
    public static Restaurant createNewFromTo(RestaurantTo newRestaurant) {
        return new Restaurant(null, newRestaurant.getName(), newRestaurant.getAddress());
    }

    public static RestaurantTo asTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public static void updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        restaurant.setAddress(restaurantTo.getAddress());
    }

    public static Map<Integer, String> getRestsWithIdsNames(List<Restaurant> restaurants){
        return restaurants
                .stream()
                .collect(Collectors.toMap(Restaurant::getId, Restaurant::getName));
    }
}
