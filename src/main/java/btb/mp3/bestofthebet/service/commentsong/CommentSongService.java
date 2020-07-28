package btb.mp3.bestofthebet.service.commentsong;

import btb.mp3.bestofthebet.model.Comment_Song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.repository.CommentSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentSongService implements ICommentSongService {

    @Autowired
    private CommentSongRepository commentSongRepository;

    public List<Comment_Song> findAll() {
        return null;
    }

    public Optional<Comment_Song> findById(Long id) {
        return null;
    }

    public void save(Comment_Song commentSong) {
        commentSongRepository.save(commentSong);
    }

    public void delete(Long id) {}

    public List<Comment_Song> deleteByUserId(Long id) {
        return commentSongRepository.deleteByUserId(id);
    }

    @Override
    public List<Comment_Song> showCommentsBySong(Song song) {
        return commentSongRepository.findAllBySongOrderByDateDesc(song);
    }
}
