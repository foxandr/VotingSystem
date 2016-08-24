package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private ProxyRestaurantRepository proxyRestaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return proxyRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return proxyRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return proxyRestaurantRepository.findOne(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return proxyRestaurantRepository.findAll();
    }

    @Override
    public Restaurant getByName(String name) {
        return proxyRestaurantRepository.getByName(name);
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return proxyRestaurantRepository.findById(id);
    }
}
