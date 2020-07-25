package btb.mp3.bestofthebet.repository.playlist;


import btb.mp3.bestofthebet.model.PlayList;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlaylistRepository extends PagingAndSortingRepository<PlayList, Long> {
            List<PlayList> findAllByOrderByViewsDesc();

            List<PlayList> findAllByOrderByLikesDesc();

            List<PlayList> findAllByOrderByCreateDateDesc();

            List<PlayList> deleteByUserId(Long id);
}
