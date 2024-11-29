package com.cs4750.p5.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.cs4750.p5.entity.User;

public interface UserService {
    public abstract ResponseEntity<User> createUser(User user);
    public abstract ResponseEntity<List<User>> getAllUsers();
    public abstract ResponseEntity<User> getUser(Integer userId);
    public abstract ResponseEntity<User> updateUser(Integer userId, User user);
    public abstract ResponseEntity<User> deleteUser(Integer userId);
}
