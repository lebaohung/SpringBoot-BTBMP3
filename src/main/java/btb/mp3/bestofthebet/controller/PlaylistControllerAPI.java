package btb.mp3.bestofthebet.controller;


import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.Playlist_Song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.service.playlist.PlaylistService;
import btb.mp3.bestofthebet.service.playlist_song.Playlist_songService;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/playlist")
public class PlaylistControllerAPI {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private Playlist_songService playlist_songService;

    @GetMapping("/lists")
    public ResponseEntity<Page<PlayList>> showPlaylist(Pageable pageable) {
        Page<PlayList> playLists = playlistService.findAll(pageable);
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }

    @GetMapping("/topView")
    public ResponseEntity<List<PlayList>> topView() {
        List<PlayList> playListView = playlistService.sortView();
        List<PlayList> topSixView = new ArrayList<>();
        topSixView.add(playListView.get(0));
        topSixView.add(playListView.get(1));
        topSixView.add(playListView.get(2));
        topSixView.add(playListView.get(3));
        topSixView.add(playListView.get(4));
        topSixView.add(playListView.get(5));
        return new ResponseEntity<List<PlayList>>(topSixView, HttpStatus.OK);
    }

    @GetMapping("/topLike")
    public ResponseEntity<List<PlayList>> topLike() {
        List<PlayList> playListLike = playlistService.sortLike();
        List<PlayList> topSixLike = new ArrayList<>();
        topSixLike.add(playListLike.get(0));
        topSixLike.add(playListLike.get(1));
        topSixLike.add(playListLike.get(2));
        topSixLike.add(playListLike.get(3));
        topSixLike.add(playListLike.get(4));
        topSixLike.add(playListLike.get(5));
        return new ResponseEntity<List<PlayList>>(topSixLike, HttpStatus.OK);
    }

    @GetMapping("/topDate")
    public ResponseEntity<List<PlayList>> topDate() {
        List<PlayList> playListDate = playlistService.sortDate();
        List<PlayList> topSixDate = new ArrayList<>();
        topSixDate.add(playListDate.get(0));
        topSixDate.add(playListDate.get(1));
        topSixDate.add(playListDate.get(2));
        topSixDate.add(playListDate.get(3));
        topSixDate.add(playListDate.get(4));
        topSixDate.add(playListDate.get(5));
        return new ResponseEntity<List<PlayList>>(topSixDate, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<PlayList> playlistID(@PathVariable Long id) {
        Optional<PlayList> playList = playlistService.findById(id);
        if (playList.isPresent()) {
            return new ResponseEntity<PlayList>(playlistService.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listUserID/{id}")
    public ResponseEntity<List<PlayList>> playListUserID(@PathVariable Long id) {
        User user = userService.finByID(id);
        List<PlayList> playListList = playlistService.playlistUserID(user);
        return new ResponseEntity<List<PlayList>>(playListList, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<PlayList> createPlaylist(@RequestBody PlayList playList) {
        playList.setLikes((long) 0);
        playList.setViews((long) 0);
        playList.setCreateDate(new Timestamp(new Date().getTime()));
        playlistService.save(playList);
        return new ResponseEntity<>(playList, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<PlayList> PlaylistByID(@PathVariable Long id) {
        PlayList playList1 = playlistService.findById(id).get();
        if (playList1 != null) {
            return new ResponseEntity<PlayList>(playList1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit")
    private ResponseEntity<PlayList> editPlaylist(@RequestBody PlayList playList) {
        playlistService.save(playList);
        return new ResponseEntity<PlayList>(playList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlayList> deletePlaylist(@PathVariable Long id) {
        Optional<PlayList> playList = playlistService.findById(id);
        if (playList.isPresent()) {
            playlistService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/playlistsong/{id}")
    private ResponseEntity<List<Song>> songPlaylisy(@PathVariable Long id) {
        PlayList playList = playlistService.findById(id).get();
        if (playList != null) {
            List<Playlist_Song> playlist_songs = playlist_songService.playlistSong(playList);
            List<Song> songList = new ArrayList<>();
            for (Playlist_Song playSong : playlist_songs) {
                songList.add(playSong.getSong());
            }
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // luu 1 song va 1 playlist vao playlist song id la id playlist
    @PutMapping("/addsong/{id}")
    private ResponseEntity<Void> saveSongToPlaylist(@PathVariable("id") Long id, @RequestBody Song song) {

        PlayList playList = playlistService.findById(id).get();
        if (playList != null && song != null) {
            Playlist_Song playlistSong = new Playlist_Song();
            playlistSong.setPlaylist(playList);
            playlistSong.setSong(song);
            playlist_songService.save(playlistSong);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // xoa 1 bai hat trong playlist id la id cua Playlist_Song

    @DeleteMapping("/th")
    private ResponseEntity<Void> deleteSongInPlaylist(@PathVariable("id") Long id){
       if(id!=null){
           playlist_songService.delete(id);
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
