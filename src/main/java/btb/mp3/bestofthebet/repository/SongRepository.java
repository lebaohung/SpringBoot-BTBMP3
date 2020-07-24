package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByUserId(Long id);

    List<Song> findAllByOrderByViewsDesc();

    List<Song> findAllByOrderByCreatDateDesc();

    Song findAllByCreatDate(String date);
}
