package com.cs4750.p5.service;

import java.util.List;

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
    
    public ResponseEntity<User> getUser(Integer id) {
        return null;
    }

    public ResponseEntity<User> updateUser(Integer id, User user) {
        return null;
    }

    public ResponseEntity<User> deleteUser(Integer id) {
        return null;
    }
}
