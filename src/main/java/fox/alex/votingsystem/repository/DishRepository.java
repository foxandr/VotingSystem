package fox.alex.votingsystem.repository;

import fox.alex.votingsystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 15.08.16.
 */
public interface DishRepository {
    Dish save(Dish dish, int rest_id);

    boolean delete(int id, int rest_id);

    Dish get(int id, int rest_id);

    List<Dish> getAll();

    List<Dish> getByDate(int rest_id, LocalDate updated);

    List<Dish> getByRerstaurant(int rest_id);
}
