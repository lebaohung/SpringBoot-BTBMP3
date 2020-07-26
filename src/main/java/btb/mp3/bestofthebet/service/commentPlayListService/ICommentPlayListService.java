package btb.mp3.bestofthebet.service.commentPlayListService;

import btb.mp3.bestofthebet.model.Comment_Playlist;
import btb.mp3.bestofthebet.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICommentPlayListService extends IService<Comment_Playlist> {

    List<Comment_Playlist> deleteByUserId(Long id);
}
