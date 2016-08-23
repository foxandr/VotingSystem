package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public interface DishService {
    Dish save(Dish dish, int rest_id);

    void delete(int id, int rest_id);

    Dish get(int id, int rest_id);

    List<Dish> getAll();

    List<Dish> getByDate(int rest_id, LocalDate updated);

    List<Dish> getByRerstaurant(int rest_id);

    void update(Dish dish, int rest_id);
}
