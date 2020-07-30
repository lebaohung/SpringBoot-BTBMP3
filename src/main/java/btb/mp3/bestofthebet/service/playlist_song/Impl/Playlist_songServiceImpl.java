package btb.mp3.bestofthebet.service.playlist_song.Impl;

import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.Playlist_Song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.repository.playlist_song.PlaylistSongRepository;
import btb.mp3.bestofthebet.service.playlist_song.Playlist_songService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Playlist_songServiceImpl implements Playlist_songService {

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @Override
    public List<Playlist_Song> playlistSong(PlayList playList) {
        return playlistSongRepository.findAllByPlaylist(playList);
    }

    @Override
    public void save(Playlist_Song playlistSong) {
        playlistSongRepository.save(playlistSong);
    }

    @Override
    public void delete(Long id) {
        playlistSongRepository.deleteById(id);
    }


    @Override
    public Playlist_Song findById(Long id) {
        return playlistSongRepository.findById(id).get();
    }

    @Override
    public void deleteBySong(Song song) {
        playlistSongRepository.deleteBySong(song);
    }

    @Override
    public List<Playlist_Song> finAllByPlaylist(PlayList playList) {
        return playlistSongRepository.findAllByPlaylist(playList);
    }

    @Override
    public Playlist_Song findByPlaylistAndSong(PlayList playList, Song song) {
        return playlistSongRepository.findByPlaylistAndSong(playList,song);
    }
}
