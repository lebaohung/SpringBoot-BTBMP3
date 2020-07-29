package btb.mp3.bestofthebet.service.singer;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SingerService implements ISingerService{
    @Autowired
    SingerRepository singerRepository;

    @Override
    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public void save(Singer model) {
        singerRepository.save(model);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Singer> findSingerByName(String name) {
        return singerRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Singer> findAllByNameEquals(String name) {
        return singerRepository.findAllByNameEquals(name);
    }
}
