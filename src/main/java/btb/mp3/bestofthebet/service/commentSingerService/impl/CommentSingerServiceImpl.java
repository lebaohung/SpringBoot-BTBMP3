package btb.mp3.bestofthebet.service.commentSingerService.impl;

import btb.mp3.bestofthebet.model.Comment_Singer;
import btb.mp3.bestofthebet.repository.CommentSingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentSingerServiceImpl implements CommentSingerService {

    @Autowired
    private CommentSingerRepository commentSingerRepository;

    public List<Comment_Singer> deleteByUserId(Long userId) {
        return commentSingerRepository.deleteByUserId(userId);
    }

    public List<Comment_Singer> findAll() {
        return null;
    }

    public Optional<Comment_Singer> findById(Long id) {return null;}
    public void save(Comment_Singer model) {}
    public void delete(Long id) {}
}
