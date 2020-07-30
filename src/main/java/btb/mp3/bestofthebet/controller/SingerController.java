package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.service.singer.ISingerService;
import btb.mp3.bestofthebet.service.singerAndSongService.ISingerAndSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/singer")
public class SingerController {
    @Autowired
    ISingerService singerService;

    @Autowired
    ISingerAndSongService singerAndSongService;

    @GetMapping()
    public ResponseEntity<List<Singer>> findAll() {
        return new ResponseEntity<List<Singer>>(singerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Singer> findSingerById(@PathVariable("id") Long id) {
        if (singerService.findById(id).get() != null) {
            return new ResponseEntity<Singer>(singerService.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // luu them 1 ca si
    @PostMapping("/save")
    public ResponseEntity<Void> saveSinger(@RequestBody Singer singer) {
        if (singer != null) {
            List<Singer> singerList = singerService.findAllByNameEquals(singer.getName());
            if(singerList.size() == 0){
                singer.setCreateDate(new Timestamp(new Date().getTime()));
                singerService.save(singer);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //lay danh sach bai hat theo id ca sy
    @GetMapping("/listSong/{id}")
    @Transactional
    public ResponseEntity<List<Song>> findSongByIdSinger(@PathVariable("id") Long id) {
        Singer singer = singerService.findById(id).get();
        if(singer!= null){
            List<Singer_And_song> singer_and_songList = singerAndSongService.findSingerAndSongBySinger(singer);
            List<Song> songList = new ArrayList();
            for (Singer_And_song s : singer_and_songList) {
                songList.add(s.getSong());
            }
            return new ResponseEntity<List<Song>>(songList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //lay danh sach ca si
    @GetMapping("/showsinger")
    public ResponseEntity<List<Singer>> showAllSinger(){
        List<Singer> singerList = singerService.findAll();
        return new ResponseEntity<List<Singer>>(singerList,HttpStatus.OK);
    }

    // tim kiem ca si theo ten

    @GetMapping("/findsinger/{name}")
    public ResponseEntity<List<Singer>> findSingerByName(@PathVariable("name") String name){
        List<Singer> singerList = singerService.findSingerByName(name);
        return new ResponseEntity<List<Singer>>(singerList,HttpStatus.OK);
    }


}
