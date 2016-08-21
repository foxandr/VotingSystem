package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Transactional(readOnly = true)
public interface ProxyVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id = :id")
    int delete(@Param("id") int id);

    @Override
    @Query("SELECT v FROM Vote v ORDER BY v.voted")
    List<Vote> findAll();

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    @Query("SELECT v FROM Vote v WHERE v.id = :id")
    Vote findOne(@Param("id") Integer integer);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :user_id ORDER BY v.voted")
    List<Vote> getByUser(@Param("user_id") int user_id);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :user_id AND v.voted = :voted")
    Vote getByDate(@Param("user_id") int user_id, @Param("voted") LocalDateTime voted);
}
