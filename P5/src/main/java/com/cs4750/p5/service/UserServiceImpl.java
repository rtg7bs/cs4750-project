package com.cs4750.p5.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cs4750.p5.entity.Playlist;
import com.cs4750.p5.entity.User;
import com.cs4750.p5.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public ResponseEntity<User> createUser(User user) {
        try {
            user.setDateJoined(LocalDate.now());
            repository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = repository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    
    public ResponseEntity<User> getUser(Integer userId) {
        Optional<User> userData = repository.findById(userId);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Playlist>> getUserPlaylists(Integer userId) {
        try {
            Optional<User> userData = repository.findById(userId);
            if (userData.isPresent()) {
                return new ResponseEntity<>(userData.get().getPlaylists(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUser(Integer userId, User user) {
        Optional<User> userData = repository.findById(userId);
        if (userData.isPresent()) {
            User oldUser = userData.get();
            oldUser.setPlanId(user.getPlanId());
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            return new ResponseEntity<>(repository.save(oldUser), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> deleteUser(Integer userId) {
        try {
            repository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
