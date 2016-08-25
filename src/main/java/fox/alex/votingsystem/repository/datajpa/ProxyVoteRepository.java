package fox.alex.votingsystem.repository.datajpa;

import fox.alex.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;;
import java.util.List;

/**
 * Created by fox on 21.08.16.
 */
@Transactional(readOnly = true)
public interface ProxyVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id = :id AND v.user.id = :user_id")
    int delete(@Param("id") int id, @Param("user_id") int user_id);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :user_id ORDER BY v.voted")
    List<Vote> findAll(@Param("user_id") int user_id);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v WHERE v.id = :id AND v.user.id = :user_id")
    Vote findOne(@Param("id") Integer integer, @Param("user_id") Integer user_id);

    //
    @Query("SELECT v FROM Vote v WHERE v.user.id = :user_id AND v.voted >= :votedStart AND v.voted <= :votedFinish")
    Vote getByDate(@Param("user_id") int user_id, @Param("votedStart") LocalDate votedStart, @Param("votedFinish") LocalDate votedFinish);
}
