package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 15.08.16.
 */
public interface DishRepository {
    Dish save(Dish dish);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getByDate(int rest_id, LocalDate updated);

    List<Dish> getByRerstaurant(int rest_id);
}
