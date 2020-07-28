package btb.mp3.bestofthebet.service.singer;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.service.IService;

import java.util.List;

public interface ISingerService extends IService<Singer> {
    List<Singer> findSingerByName(String name);
}
