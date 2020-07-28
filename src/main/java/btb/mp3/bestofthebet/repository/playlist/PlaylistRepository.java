package btb.mp3.bestofthebet.repository.playlist;


import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlaylistRepository extends PagingAndSortingRepository<PlayList, Long> {
            List<PlayList> findTop6ByOrderByViewsDesc();
            List<PlayList> findTop6ByOrderByLikesDesc();

            List<PlayList> findTop6ByOrderByCreateDateDesc();

            List<PlayList> deleteByUserId(Long id);

            List<PlayList> findAllByUser(User user);

            List<PlayList> findAllByNameContaining(String name);
}
