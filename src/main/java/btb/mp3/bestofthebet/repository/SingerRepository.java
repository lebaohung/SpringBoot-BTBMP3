package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SingerRepository extends JpaRepository<Singer,Long> {
    List<Singer> findAllByNameContaining(String name);
    List<Singer> findAllByNameEquals(String name);
    Optional<Singer> findById(Long id);
}
