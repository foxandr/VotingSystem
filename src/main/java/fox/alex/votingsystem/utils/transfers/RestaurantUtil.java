package fox.alex.votingsystem.utils.transfers;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.to.RestaurantTo;

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
}
