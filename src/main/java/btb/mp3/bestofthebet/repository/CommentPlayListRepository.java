package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Comment_Playlist;
import btb.mp3.bestofthebet.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentPlayListRepository extends JpaRepository<Comment_Playlist, Long> {

    List<Comment_Playlist> deleteByUserId(Long id);

    List<Comment_Playlist> findAllByPlaylistOrderByDateDesc(PlayList playlist);
}
