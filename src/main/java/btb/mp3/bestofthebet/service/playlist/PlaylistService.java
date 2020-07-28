package btb.mp3.bestofthebet.service.playlist;

import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    Page<PlayList> findAll(Pageable pageable);

    Optional<PlayList> findById(Long id);

    List<PlayList> sortView ();
    List<PlayList> sortLike ();
    List<PlayList> sortDate ();
    List<PlayList> playlistUserID (User user);

    void save(PlayList playList);

    void remove(Long id);

    List<PlayList> deleteByUserId(Long id);
    List<PlayList> findPlaylistByName(String name);
}
