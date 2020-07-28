package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Comment_Song;
import btb.mp3.bestofthebet.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentSongRepository extends JpaRepository<Comment_Song, Long> {

    List<Comment_Song> deleteByUserId(Long id);

    List<Comment_Song> findAllBySongOrderByDateDesc(Song song);

}
