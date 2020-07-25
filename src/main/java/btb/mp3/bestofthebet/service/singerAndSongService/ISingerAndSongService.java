package btb.mp3.bestofthebet.service.singerAndSongService;

import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.service.IService;

public interface ISingerAndSongService extends IService<Singer_And_song> {
    Singer_And_song findBySong(Song song);
}
