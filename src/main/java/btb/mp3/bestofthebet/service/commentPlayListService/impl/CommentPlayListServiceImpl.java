package btb.mp3.bestofthebet.service.commentPlayListService.impl;

import btb.mp3.bestofthebet.model.Comment_Playlist;
import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.repository.CommentPlayListRepository;
import btb.mp3.bestofthebet.service.commentPlayListService.ICommentPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentPlayListServiceImpl implements ICommentPlayListService {

    @Autowired
    private CommentPlayListRepository commentPlayListRepository;

    public List<Comment_Playlist> findAll() {
        return null;
    }

    public Optional<Comment_Playlist> findById(Long id) {
        return null;
    }

    public void save(Comment_Playlist commentSong) {
        commentPlayListRepository.save(commentSong);
    }

    public void delete(Long id) {}

    public List<Comment_Playlist> deleteByUserId(Long id) {
        return commentPlayListRepository.deleteByUserId(id);
    }

    @Override
    public List<Comment_Playlist> showCommentByPlaylist(PlayList playList) {
        return commentPlayListRepository.findAllByPlaylistOrderByDateDesc(playList);
    }
}
