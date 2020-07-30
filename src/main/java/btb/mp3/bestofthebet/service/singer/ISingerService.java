package btb.mp3.bestofthebet.service.singer;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.service.IService;

import java.util.List;
import java.util.Optional;

public interface ISingerService extends IService<Singer> {
    List<Singer> findSingerByName(String name);
    List<Singer> findAllByNameEquals(String name);
    Optional<Singer> findById(Long id);
}
