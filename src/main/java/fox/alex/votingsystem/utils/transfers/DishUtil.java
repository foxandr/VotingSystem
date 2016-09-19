package fox.alex.votingsystem.utils.transfers;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.to.DishTo;

/**
 * Created by fox on 07.09.16.
 */
public class DishUtil {
    public static Dish createNewFromTo(DishTo newDish) {
        return new Dish(null, newDish.getName(), newDish.getPrice());
    }

    public static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static void updateFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
    }
}
