package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SingerRepository extends JpaRepository<Singer,Long> {
    List<Singer> findAllByNameContaining(String name);
}
