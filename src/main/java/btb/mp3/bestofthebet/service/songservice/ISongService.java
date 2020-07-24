package btb.mp3.bestofthebet.service.songservice;

import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.service.IService;

import java.util.List;

public interface ISongService extends IService<Song> {

    List<Song> findSongByUserId(Long id);

    List<Song> findTop6View();

    List<Song> findTop6New();

    Song findByCreatDate(String date);

}
