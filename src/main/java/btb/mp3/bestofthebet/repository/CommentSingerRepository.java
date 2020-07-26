package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Comment_Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentSingerRepository extends JpaRepository<Comment_Singer, Long> {

    List<Comment_Singer> deleteByUserId(Long id);
}
