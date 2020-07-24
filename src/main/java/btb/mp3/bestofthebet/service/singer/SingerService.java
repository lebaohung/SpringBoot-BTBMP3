package btb.mp3.bestofthebet.service.singer;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.repository.SingerReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SingerService implements ISingerService{
    @Autowired
    SingerReposiory singerReposiory;

    @Override
    public List<Singer> findAll() {
        return singerReposiory.findAll();
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Singer model) {

    }

    @Override
    public void delete(Long id) {

    }
}
