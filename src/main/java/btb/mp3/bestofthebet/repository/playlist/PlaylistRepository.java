package btb.mp3.bestofthebet.repository.playlist;


import btb.mp3.bestofthebet.model.PlayList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlaylistRepository extends PagingAndSortingRepository<PlayList, Long> {
}
