package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SingerAndSongReposiory extends JpaRepository<Singer_And_song,Long> {
    Singer_And_song findAllBySong(Song song);
    void deleteBySong(Song song);
    List<Singer_And_song> findAllBySinger(Singer singer);

}
