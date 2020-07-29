package btb.mp3.bestofthebet.service.user.impl;

import btb.mp3.bestofthebet.model.User;
import btb.mp3.bestofthebet.repository.UserRepository;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User finByID(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {return null;}

    @Override
    public Optional<User> findById(Long id) {
        return null;
    }

    @Override
    public void save(User model) { }

    @Override
    public void delete(Long id) {}

    @Override
    public List<Object> statisticUsersByCreateDate() {
        return userRepository.findAllUsersByCreateDate();
    }
}
