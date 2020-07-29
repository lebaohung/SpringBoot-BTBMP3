package btb.mp3.bestofthebet.controller.admin.statistic;

import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.repository.UserRepository;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*@CrossOrigin("http://localhost:4201")*/
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/statistic")
public class StatisticController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users-date")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Object>> getStatisticNewRegister() {
        List<Object> test = userRepository.findAllUsersByCreateDate();
        return ResponseEntity.ok(test);

    }
}
