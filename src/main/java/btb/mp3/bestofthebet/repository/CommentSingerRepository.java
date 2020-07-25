package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Comment_Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentSingerRepository extends JpaRepository<Comment_Singer, Long> {

    List<Comment_Singer> deleteByUserId(Long id);
}
