package com.socialapp.app.service;

import com.socialapp.app.model.User;
import com.socialapp.app.repository.TaskRepository;
import com.socialapp.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("No user with given id"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("No user with given email"));
    }

    //TODO: IT'S SUPPOSED TO BE A DTO
    public void updateUser(User userToUpdate, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("No user with given id"));
        user.setEmail(userToUpdate.getEmail());
        user.setUsername(userToUpdate.getUsername());
        user.setPassword(userToUpdate.getPassword());
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
