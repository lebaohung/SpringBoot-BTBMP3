package btb.mp3.bestofthebet.service.songservice;

import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.service.IService;

import java.util.Date;
import java.util.List;

public interface ISongService extends IService<Song> {

    List<Song> findSongByUser(User user);

    List<Song> findTop6View();

    List<Song> findTop6New();

    List<Song> findTop6Liked();

    Song findByCreatDate(Date date);

    List<Song> findByName(String name);

}
