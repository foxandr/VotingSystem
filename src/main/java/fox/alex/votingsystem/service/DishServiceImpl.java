package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.repository.DishRepository;
import fox.alex.votingsystem.utils.exception.ExceptionUtil;
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
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Dish> getByDate(int rest_id, LocalDate updated) {
        return repository.getByDate(rest_id, updated);
    }

    @Override
    public List<Dish> getByRerstaurant(int rest_id) {
        return repository.getByRerstaurant(rest_id);
    }

    @Override
    public void update(Dish dish, int rest_id) {
        repository.save(dish, rest_id);
    }
}
