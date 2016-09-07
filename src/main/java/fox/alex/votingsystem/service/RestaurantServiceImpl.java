package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.repository.RestaurantRepository;
import fox.alex.votingsystem.to.RestaurantTo;
import fox.alex.votingsystem.utils.exception.ExceptionUtil;
import fox.alex.votingsystem.utils.exception.NotFoundException;
import fox.alex.votingsystem.utils.transfers.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by fox on 21.08.16.
 */
@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant getByName(String name) throws NotFoundException {
        Objects.requireNonNull(name, "Name must not be empty");
        return ExceptionUtil.checkNotFound(repository.getByName(name), "name=" + name);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(Restaurant restaurant) {
        repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(RestaurantTo restaurantTo) {
        Restaurant restaurant = get(restaurantTo.getId());
        RestaurantUtil.updateFromTo(restaurant, restaurantTo);
        repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {}

    @Override
    public Restaurant getWithDishes(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithDishes(id), id);
    }
}
