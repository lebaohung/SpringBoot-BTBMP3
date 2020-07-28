package btb.mp3.bestofthebet.repository.playlist_song;

import btb.mp3.bestofthebet.model.Category;
import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.Playlist_Song;
import btb.mp3.bestofthebet.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<Playlist_Song,Long> {
    List<Playlist_Song> findAllByPlaylist(PlayList playList);

    void deleteBySong(Song song);

    Playlist_Song findByPlaylistAndSong(PlayList playList,Song song);
}
