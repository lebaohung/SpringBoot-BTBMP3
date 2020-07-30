package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("Select user.createDate, count(user) from User user group by function('date', user.createDate)")
    List<Object> findAllUsersByCreateDate();

}
