package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.*;
import btb.mp3.bestofthebet.model.response.MessageResponse;
import btb.mp3.bestofthebet.service.commentsong.ICommentSongService;
import btb.mp3.bestofthebet.service.playlist_song.Playlist_songService;
import btb.mp3.bestofthebet.service.singer.ISingerService;
import btb.mp3.bestofthebet.service.singerAndSongService.ISingerAndSongService;
import btb.mp3.bestofthebet.service.songservice.ISongService;
import btb.mp3.bestofthebet.service.songservice.SongService;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    ISongService songService;

    @Autowired
    ISingerAndSongService singerAndSongService;

    @Autowired
    ISingerService singerService;

    @Autowired
    UserService userService;

    @Autowired
    ICommentSongService commentSongService;

    @Autowired
    private Playlist_songService playlist_songService;

    // xoa bai hat theo id bai hat (ok)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Song> deleteSong(@PathVariable("id") Long id) {
        Song song = songService.findById(id).get();
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        singerAndSongService.deleteBySong(song);
        songService.delete(id);
        playlist_songService.deleteBySong(song);
        return new ResponseEntity<Song>(song, HttpStatus.OK);
    }

    // tao moi 1 bai hat(ok)
    @PostMapping("/{id}")

    public ResponseEntity<Void> CreateSong(@RequestBody Song song, @PathVariable("id") Long id) {
        song.setLikes((long) 0);
        song.setViews((long) 0);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
        song.setCreatDate(new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Timestamp(new Date().getTime()))));
        song.setStatus(true);
        songService.save(song);
        Date date = song.getCreatDate();
        Song selectSong = songService.findByCreatDate(date);
        Long songId = selectSong.getId();
        Singer_And_song singerAndSong = new Singer_And_song();
        singerAndSong.setSinger(singerService.findById(id).get());
        singerAndSong.setSong(songService.findById(songId).get());
        singerAndSongService.save(singerAndSong);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    // lay list bai hat theo user id (ok)
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Song>> listSongByUserId(@PathVariable("id") Long id) {
        User user = userService.finByID(id);
        return new ResponseEntity<List<Song>>(songService.findSongByUser(user), HttpStatus.OK);
    }

    //     lay 1 bai hat theo id bai hat(ok)
    @GetMapping("/{id}")
    public ResponseEntity<Song> findSongByIdSong(@PathVariable("id") Long songId) {
        if (songService.findById(songId).get() != null) {
            return new ResponseEntity<Song>(songService.findById(songId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // lay singer and song theo song(ok)
    @GetMapping("/singerandsong/{id}")
    public ResponseEntity<Singer> findSingerBySong(@PathVariable("id") Long id) {
        Song song = songService.findById(id).get();
        if (song != null) {
            Singer singer = singerAndSongService.findBySong(song).getSinger();
            return new ResponseEntity<Singer>(singer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //edit 1 bai hat (ok)
    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> EditSong(@RequestBody Song song, @PathVariable("id") Long id) {
        songService.save(song);
        Long songId = song.getId();
        Singer_And_song singerAndSong = singerAndSongService.findBySong(song);
        singerAndSong.setSinger(singerService.findById(id).get());
        singerAndSong.setSong(songService.findById(songId).get());
        singerAndSongService.save(singerAndSong);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    // top 6 views
    @GetMapping("/topview")
    public ResponseEntity<List<Song>> Top6() {
        List<Song> songList = songService.findTop6View();
        if (songList != null) {
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/topliked")
    public ResponseEntity<List<Song>> topLiked() {
        List<Song> songList = songService.findTop6Liked();
        if (songList != null) {
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        }
        return new ResponseEntity<List<Song>>(HttpStatus.NOT_FOUND);
    }

    // top 6 bai hat moi tao
    @GetMapping("/newcreat")
    public ResponseEntity<List<Song>> newCreat() {
        List<Song> songList = songService.findTop6New();
        if (songList != null) {
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        }
        return new ResponseEntity<List<Song>>(HttpStatus.FOUND);
    }

    // tim kiem bai hat theo ten(ok)

    @GetMapping("/findsong/{name}")
    public ResponseEntity<List<Song>> findSongByName(@PathVariable("name") String name) {
        List<Song> songList = songService.findByName(name);
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    // hien thi comment theo id song

    @GetMapping("/showcomment/{id}")
    @Transactional
    public ResponseEntity<List<Comment_Song>> showComment(@PathVariable("id") Long id) {
        Song song = songService.findById(id).get();
        if (song != null) {
            List<Comment_Song> comment_playlists = commentSongService.showCommentsBySong(song);
            return new ResponseEntity<List<Comment_Song>>(comment_playlists, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // them 1 comment vao song
    @PostMapping("/savecommentSong")
    @Transactional
    public ResponseEntity<Void> saveCommentSong(@RequestBody Comment_Song comment_song) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (comment_song != null) {
            comment_song.setDate(localDateTime);
//            comment_song.setDate(new Timestamp(new Date().getTime()));
            commentSongService.save(comment_song);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
