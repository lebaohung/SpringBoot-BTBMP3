package btb.mp3.bestofthebet.controller.admin.crud;

import btb.mp3.bestofthebet.exception.ResourceNotFoundException;
import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;/**/

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/admin/crud-user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id ::" + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> blockUser(@PathVariable(value = "id") Long userId,
                                          @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
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
