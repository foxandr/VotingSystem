package fox.alex.votingsystem.web.dishes;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.service.DishService;
import fox.alex.votingsystem.to.DishTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 19.09.16.
 */
public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish create(Dish dish, int rest_id) {
        dish.setId(null);
        log.info("create " + dish);
        return service.save(dish, rest_id);
    }

    public void delete(int id, int rest_id) {
        log.info("delete " + id);
        service.delete(id, rest_id);
    }

    public Dish get(int id, int rest_id) {
        log.info("get " + id);
        return service.get(id, rest_id);
    }

    public List<Dish> getAll(int rest_id) {
        log.info("getAll dishes");
        return service.getAll(rest_id);
    }

    public List<Dish> getByDate(int rest_id, LocalDate updated) {
        log.info("get dish by date " + updated);
        return service.getByDate(rest_id, updated);
    }

    public Dish getWithRerstaurant(int id, int rest_id) {
        log.info("get dish " + id + "with restaurant" + rest_id);
        return service.getWithRerstaurant(id, rest_id);
    }

    public void update(Dish dish, int id, int rest_id) {
        dish.setId(id);
        log.info("update " + dish);
        service.update(dish, rest_id);
    }

    public void update(DishTo dishTo, int id, int rest_id) {
        dishTo.setId(id);
        log.info("update " + dishTo);
        service.update(dishTo, rest_id);
    }
}
