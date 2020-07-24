package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Singer_And_song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerAndSongReposiory extends JpaRepository<Singer_And_song,Long> {
}
