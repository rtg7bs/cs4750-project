package com.cs4750.p5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cs4750.p5.entity.User;
import com.cs4750.p5.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public ResponseEntity<User> createUser(User user) {
        return null;
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

    public ResponseEntity<User> updateUser(Integer userId, User user) {
        return null;
    }

    public ResponseEntity<User> deleteUser(Integer userId) {
        return null;
    }
}
