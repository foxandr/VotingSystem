package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Transactional(readOnly = true)
public interface ProxyRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :id")
    int delete(@Param("id") int id);

    @Override
    @Query("SELECT r FROM Restaurant r ORDER BY r.name")
    List<Restaurant> findAll();

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Restaurant findOne(Integer integer);

    @EntityGraph(value = Restaurant.GRAPH_WITH_DISHES)
    Restaurant getByName(String name);

    @EntityGraph(value = Restaurant.GRAPH_WITH_DISHES)
    Restaurant findById(Integer integer);

}
