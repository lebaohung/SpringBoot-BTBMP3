package btb.mp3.bestofthebet.controller.admin.crud;

import btb.mp3.bestofthebet.exception.ResourceNotFoundException;
import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.repository.CommentSongRepository;
import btb.mp3.bestofthebet.repository.SongRepository;
import btb.mp3.bestofthebet.repository.UserRepository;
import btb.mp3.bestofthebet.service.commentPlayListService.ICommentPlayListService;
import btb.mp3.bestofthebet.service.commentSingerService.impl.CommentSingerService;
import btb.mp3.bestofthebet.service.commentsong.ICommentSongService;
import btb.mp3.bestofthebet.service.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4201")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin/crud-user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ICommentSongService commentSongService;

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private ICommentPlayListService commentPlayListService;

    @Autowired
    private CommentSingerService commentSingerService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id ::" + userId));

        Long deletedUserId = user.getId();

        commentSingerService.deleteByUserId(deletedUserId);
        commentPlayListService.deleteByUserId(deletedUserId);
        commentSongService.deleteByUserId(deletedUserId);
        songRepository.deleteByUserId(deletedUserId);
        playlistService.deleteByUserId(deletedUserId);
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<User> blockUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for id :: " + userId));

        Boolean currentStatusUser = user.isStatus();
        if (currentStatusUser) {
            user.setStatus(false);
        } else {
            user.setStatus(true);
        }
        final User blockedUser = userRepository.save(user);
        return ResponseEntity.ok(blockedUser);
    }
}
