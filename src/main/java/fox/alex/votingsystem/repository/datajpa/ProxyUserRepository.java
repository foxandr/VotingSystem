package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fox on 17.08.16.
 */
@Transactional(readOnly = true)
public interface ProxyUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    int delete(@Param("id") int id);

    @Override
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email")
    List<User> findAll();

    @Override
    @Transactional
    User save(User user);

    @Override
    @EntityGraph(value = User.GRAPH_WITH_ROLES)
    User findOne(Integer integer);

    @EntityGraph(value = User.GRAPH_WITH_ROLES)
    User getByEmail(String email);

    @EntityGraph(value = User.GRAPH_WITH_ROLES_AND_VOTES)
    User findById(Integer id);

}
