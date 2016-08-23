package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    private ProxyDishRepository proxyDishRepository;

    @Autowired
    private ProxyRestaurantRepository proxyRestaurantRepository;

    @Override
    public Dish save(Dish dish, int rest_id) {
        if (!dish.isNew() && get(dish.getId(), rest_id) == null){
            return null;
        }
        dish.setRestaurant(proxyRestaurantRepository.findOne(rest_id));
        return proxyDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int rest_id) {
        return proxyDishRepository.delete(id, rest_id) != 0;
    }

    @Override
    public Dish get(int id, int rest_id) {
        return proxyDishRepository.findOne(id, rest_id);
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
