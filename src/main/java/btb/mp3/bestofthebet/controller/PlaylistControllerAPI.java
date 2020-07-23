package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.service.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("playlist")
public class PlaylistControllerAPI {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/lists")
    public ResponseEntity<Page<PlayList>> showPlaylist (Pageable pageable){
        Page<PlayList> playLists = playlistService.findAll(pageable);
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }

//    @GetMapping("/topView")
//    public ResponseEntity<List<PlayList>> topView(){
//        List<PlayList> playListView = playlistService.sortView();
//        List<PlayList> topSixView = new ArrayList<>();
//        topSixView.add(playListView.get(0));
//        topSixView.add(playListView.get(1));
//        topSixView.add(playListView.get(2));
//        topSixView.add(playListView.get(3));
//        topSixView.add(playListView.get(4));
//        topSixView.add(playListView.get(5));
//        return new ResponseEntity<List<PlayList>>(playlistService.sortView(), HttpStatus.OK);
//    }
//
//    @GetMapping("/topLike")
//    public ResponseEntity<List<PlayList>> topLike(){
//        List<PlayList> playListLike = playlistService.sortLike();
//        List<PlayList> topSixLike = new ArrayList<>();
//        topSixLike.add(playListLike.get(0));
//        topSixLike.add(playListLike.get(1));
//        topSixLike.add(playListLike.get(2));
//        topSixLike.add(playListLike.get(3));
//        topSixLike.add(playListLike.get(4));
//        topSixLike.add(playListLike.get(5));
//        return new ResponseEntity<List<PlayList>>(playlistService.sortLike(), HttpStatus.OK);
//    }
//
//    @GetMapping("/topDate")
//    public ResponseEntity<List<PlayList>> topDate(){
//        List<PlayList> playListDate = playlistService.sortDate();
//        List<PlayList> topSixDate = new ArrayList<>();
//        topSixDate.add(playListDate.get(0));
//        topSixDate.add(playListDate.get(1));
//        topSixDate.add(playListDate.get(2));
//        topSixDate.add(playListDate.get(3));
//        topSixDate.add(playListDate.get(4));
//        topSixDate.add(playListDate.get(5));
//        return new ResponseEntity<List<PlayList>>(playlistService.sortDate(), HttpStatus.OK);
//    }

    @GetMapping("/list/{id}")
    public ResponseEntity<PlayList> playlistID(@PathVariable Long id){
        Optional<PlayList> playList = playlistService.findById(id);
        if (playList.isPresent()) {
            return new ResponseEntity<PlayList>(playlistService.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/creates")
    public ResponseEntity<PlayList> createPlaylist(@RequestBody PlayList playList){
        playlistService.save(playList);
        return new ResponseEntity<>(playList, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlayList> editPlaylist(@PathVariable Long id, @RequestBody PlayList playList){
        Optional<PlayList> playList1 = playlistService.findById(id);
        if (playList1.isPresent()){
//            playList.setId(id);
            playlistService.save(playList);
            return  new ResponseEntity<>(playList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<PlayList> deletePlaylist(@PathVariable Long id){
        Optional<PlayList> playList = playlistService.findById(id);
        if (playList.isPresent()){
            playlistService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
