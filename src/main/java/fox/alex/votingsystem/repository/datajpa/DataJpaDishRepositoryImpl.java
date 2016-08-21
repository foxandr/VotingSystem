package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    private ProxyDishRepository proxyDishRepository;

    @Override
    public Dish save(Dish dish) {
        return proxyDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return proxyDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return proxyDishRepository.findOne(id);
    }

    @Override
    public List<Dish> getAll() {
        return proxyDishRepository.findAll();
    }

    @Override
    public List<Dish> getByDate(int rest_id, LocalDate updated) {
        return proxyDishRepository.getByDate(rest_id, updated);
    }

    @Override
    public List<Dish> getByRerstaurant(int rest_id) {
        return proxyDishRepository.getByRestaurant(rest_id);
    }
}
