package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByUser(User user);

    List<Song> findTop6ByOrderByViewsDesc();

    List<Song> findTop6ByOrderByLikesDesc();

    List<Song> findTop6ByOrderByCreatDateDesc();

    List<Song> deleteByUserId(Long id);

    Song findAllByCreatDate(Date date);

    List<Song> findAllByNameContaining(String name);

}
