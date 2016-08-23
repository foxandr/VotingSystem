package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Transactional(readOnly = true)
public interface ProxyDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id = :id AND d.restaurant.id = :rest_id")
    int delete(@Param("id") int id, @Param("rest_id") int rest_id);

    @Override
    @Query("SELECT d FROM Dish d ORDER BY d.updated")
    List<Dish> findAll();

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.restaurant.id = :rest_id")
    Dish findOne(@Param("id") Integer integer, @Param("rest_id") Integer rest_id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :rest_id ORDER BY d.price")
    List<Dish> getByRestaurant(@Param("rest_id") int rest_id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :rest_id AND d.updated = :updated ORDER BY d.updated DESC")
    List<Dish> getByDate(@Param("rest_id") int rest_id, @Param("updated") LocalDate updated);
}
