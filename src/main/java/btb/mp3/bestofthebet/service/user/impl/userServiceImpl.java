package btb.mp3.bestofthebet.service.user.impl;

import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.repository.UserRepository;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User finByID(Long id) {
        return userRepository.findById(id).get();
    }
}
