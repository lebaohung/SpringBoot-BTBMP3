package btb.mp3.bestofthebet.service.singerAndSongService;

import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.repository.SingerAndSongReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SingerAndSongService implements ISingerAndSongService{
    @Autowired
    SingerAndSongReposiory singerAndSongReposiory;

    @Override
    public List<Singer_And_song> findAll() {
        return null;
    }

    @Override
    public Optional<Singer_And_song> findById(Long id) {
        return singerAndSongReposiory.findById(id);
    }

    @Override
    public void save(Singer_And_song model) {
        singerAndSongReposiory.save(model);
    }

    @Override
    public void delete(Long id) {
        singerAndSongReposiory.deleteById(id);
    }

    @Override
    public Singer_And_song findBySong(Song song) {
        return singerAndSongReposiory.findAllBySong(song);
    }

    @Override
    public void deleteBySong(Song song) {
        singerAndSongReposiory.deleteBySong(song);
    }

    @Override
    public List<Singer_And_song> findSingerAndSongBySinger(Singer singer) {
        return singerAndSongReposiory.findAllBySinger(singer);
    }
}
