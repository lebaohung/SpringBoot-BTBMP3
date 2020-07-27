package btb.mp3.bestofthebet.service.commentsong;

import btb.mp3.bestofthebet.model.Comment_Song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.service.IService;

import java.util.List;

public interface ICommentSongService extends IService<Comment_Song> {

    List<Comment_Song> deleteByUserId(Long userId);
    List<Comment_Song> showCommentsBySong(Song song);
}
