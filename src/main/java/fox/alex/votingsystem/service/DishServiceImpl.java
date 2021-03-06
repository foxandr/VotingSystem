package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.repository.DishRepository;
import fox.alex.votingsystem.to.DishTo;
import fox.alex.votingsystem.utils.exception.ExceptionUtil;
import fox.alex.votingsystem.utils.transfers.DishUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Service("dishService")
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public Dish save(Dish dish, int rest_id) {
        return repository.save(dish, rest_id);
    }

    @Override
    public void delete(int id, int rest_id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, rest_id), id);
    }

    @Override
    public Dish get(int id, int rest_id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, rest_id), id);
    }

    @Override
    public List<Dish> getAll(int rest_id) {
        return repository.getAll(rest_id);
    }

    @Override
    public List<Dish> getByDate(int rest_id, LocalDate updated) {
        return repository.getByDate(rest_id, updated);
    }

    @Override
    public Dish getWithRerstaurant(int id, int rest_id) {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithRerstaurant(id, rest_id), id);
    }

    @Override
    public void update(DishTo dishTo, int rest_id) {
        Dish dish = get(dishTo.getId(), rest_id);
        DishUtil.updateFromTo(dish, dishTo);
        repository.save(dish, rest_id);
    }

    @Override
    public void update(Dish dish, int rest_id) {
        repository.save(dish, rest_id);
    }
}
