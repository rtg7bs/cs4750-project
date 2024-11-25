package com.cs4750.p5.controller;

import com.cs4750.p5.entity.User;
import com.cs4750.p5.service.UserService;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService service;

	UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return service.createUser(user);
	}

	@GetMapping("")
	public ResponseEntity<List<User>> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		return service.getUser(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
		return service.updateUser(id, user);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		return service.deleteUser(id);
	}
}
