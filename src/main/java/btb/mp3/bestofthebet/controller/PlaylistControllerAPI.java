package btb.mp3.bestofthebet.controller;


import btb.mp3.bestofthebet.model.*;
import btb.mp3.bestofthebet.service.commentPlayListService.ICommentPlayListService;
import btb.mp3.bestofthebet.service.playlist.PlaylistService;
import btb.mp3.bestofthebet.service.playlist_song.Playlist_songService;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.objects.NativeDate.setDate;

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

    @Autowired
    private ICommentPlayListService commentPlayListService;

    @GetMapping("/lists")
    public ResponseEntity<Page<PlayList>> showPlaylist(Pageable pageable) {
        Page<PlayList> playLists = playlistService.findAll(pageable);
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }

    @GetMapping("/topView")
    public ResponseEntity<List<PlayList>> topView() {
        List<PlayList> playListView = playlistService.sortView();

        if (playListView != null) {
            return new ResponseEntity<List<PlayList>>(playListView, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/topLike")
    public ResponseEntity<List<PlayList>> topLike() {
        List<PlayList> playListLike = playlistService.sortLike();

        if (playListLike != null) {
            return new ResponseEntity<List<PlayList>>(playListLike, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/topDate")
    public ResponseEntity<List<PlayList>> topDate() {
        List<PlayList> playListDate = playlistService.sortDate();

        if (playListDate != null) {
            return new ResponseEntity<List<PlayList>>(playListDate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    // luu 1 song vao 1 playlist vao playlist song id la id playlist
    @PutMapping("/addsong/{id}")
    private ResponseEntity<Void> saveSongToPlaylist(@PathVariable("id") Long id, @RequestBody Song song) {

        PlayList playList = playlistService.findById(id).get();
        if (playList != null && song != null) {
            Playlist_Song playlistSong = new Playlist_Song();
            playlistSong.setPlaylist(playList);
            playlistSong.setSong(song);
            if(playlist_songService.findByPlaylistAndSong(playList,song)!=null){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            playlist_songService.save(playlistSong);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // xoa 1 bai hat trong playlist id la id cua Playlist_Song

//    @DeleteMapping("/deleteSonginPlaylist/{id}")
//    private ResponseEntity<Void> deleteSongInPlaylist(@PathVariable("id") Long id) {
//        if (id != null) {
//            playlist_songService.delete(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }

    @DeleteMapping("/deleteSonginPlaylist/{playlistid}/{songid}")
    private ResponseEntity<Void> deleteSongInPlaylist(@PathVariable("playlistid") Long playlistid, @PathVariable("songid") Long songid) {
        PlayList playList = playlistService.findById(playlistid).get();
        List<Playlist_Song> playlistSong = playlist_songService.finAllByPlaylist(playList);
        for (int i = 0; i < playlistSong.size(); i++) {
            if (playlistSong.get(i).getSong().getId() == songid) {
                playlist_songService.delete(playlistSong.get(i).getId());
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // them 1 comment vao playlist
    @PostMapping("/savecommentPlaylist")
    private ResponseEntity<Void> saveCommentPlaylist(@RequestBody Comment_Playlist comment_playlist) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (comment_playlist != null) {
            comment_playlist.setDate(localDateTime);
            commentPlayListService.save(comment_playlist);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // hien thi comment theo id playlist(ok)

    @GetMapping("/showcomment/{id}")
    private ResponseEntity<List<Comment_Playlist>> showComment(@PathVariable("id") Long id) {
        PlayList playList = playlistService.findById(id).get();
        if (playList != null) {
            List<Comment_Playlist> comment_playlists = commentPlayListService.showCommentByPlaylist(playList);
            return new ResponseEntity<List<Comment_Playlist>>(comment_playlists, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // tim kiem playlist theo ten

    @GetMapping("/findplaylist/{name}")
    private ResponseEntity<List<PlayList>> findPlaylistByName(@PathVariable("name") String name){
        List<PlayList> playLists = playlistService.findPlaylistByName(name);
        return new ResponseEntity<List<PlayList>>(playLists,HttpStatus.OK);
    }


}
