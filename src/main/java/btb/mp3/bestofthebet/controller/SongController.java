package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.model.response.MessageResponse;
import btb.mp3.bestofthebet.service.singer.ISingerService;
import btb.mp3.bestofthebet.service.singerAndSongService.ISingerAndSongService;
import btb.mp3.bestofthebet.service.songservice.ISongService;
import btb.mp3.bestofthebet.service.songservice.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    // xoa bai hat theo id bai hat (can xem xet)
    @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable("id") Long id) {
        Song song = songService.findById(id).get();
        if (song == null && song.isStatus() == false) {
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        song.setStatus(false);
        songService.save(song);
        return new ResponseEntity<Song>(HttpStatus.OK);
    }

    // tao moi 1 bai hat
    @PostMapping("/{id}")
    /*@PreAuthorize("hasRole('ROLE_USER')")*/
    public ResponseEntity<Void> CreateSong(@RequestBody Song song,@PathVariable("id") Long id) {
          songService.save(song);
//        Song selectSong = songService.findByCreatDate(song.getCreatDate().toString());
//        Long songId = selectSong.getId();
//        Singer_And_song singerAndSong = new Singer_And_song();
//        singerAndSong.setSinger(singerService.findById(id).get());
//        singerAndSong.setSong(songService.findById(songId).get());
//        singerAndSongService.save(singerAndSong);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

//    @PostMapping()
//    public ResponseEntity<Void>CreateSong(){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



    // lay list bai hat theo user id (can xem xet)
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Song>> listSongByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<List<Song>>(songService.findSongByUserId(id), HttpStatus.OK);
    }

//     lay 1 bai hat theo id bai hat(ok)
    @GetMapping("/{id}")
    public ResponseEntity<Song> findSongByIdSong(@PathVariable("id") Long songId) {
        if (songService.findById(songId).get() != null) {
            return new ResponseEntity<Song>(songService.findById(songId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    //edit 1 bai hat (can xem xet)
    @PutMapping("/edit")
    public ResponseEntity<Void> EditSong(@RequestBody Song song) {
        songService.save(song);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // top 6 views
    @GetMapping("/topview")
    public ResponseEntity<List<Song>> Top6() {
        return new ResponseEntity<List<Song>>(songService.findTop6View(), HttpStatus.OK);
    }

    // top 6 bai hat moi tao
    @GetMapping("/newcreat")
    public ResponseEntity<List<Song>> newCreat() {
        return new ResponseEntity<List<Song>>(songService.findTop6New(), HttpStatus.OK);
    }


}
