package com.example.springboot.dbservice;

import com.example.springboot.entity.Post;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDBService implements IUserDBService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Post> getPosts(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getPosts();
        } else {
            throw new RuntimeException("Invalid user id " + userId);
        }
    }
}
