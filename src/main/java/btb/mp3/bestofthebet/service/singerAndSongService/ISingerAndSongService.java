package btb.mp3.bestofthebet.service.singerAndSongService;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.service.IService;

import java.util.List;

public interface ISingerAndSongService extends IService<Singer_And_song> {
    Singer_And_song findBySong(Song song);
    void deleteBySong(Song song);
    List<Singer_And_song> findSingerAndSongBySinger(Singer singer);
}
