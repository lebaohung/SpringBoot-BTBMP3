package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.service.security.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list/{id}")
    public ResponseEntity<PlayList> playlistID(@PathVariable Long id){
        Optional<PlayList> playList = playlistService.findById(id);
        if (playList != null) {
            playlistService.findById(id);
            return new ResponseEntity<>(HttpStatus.OK);
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
        if (playList1 != null){
            playList.setId(id);
            playlistService.save(playList);
            return  new ResponseEntity<>(playList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
