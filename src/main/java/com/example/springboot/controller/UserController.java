package com.example.springboot.controller;

import com.example.springboot.dbservice.UserDBService;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

	@Autowired
	UserDBService userDBService;




	@PostMapping(value = "/users")
	public User execute(@RequestBody User user) {
		return userDBService.saveUser(user);
	}

	@GetMapping(value = "/users")
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		for(User user : userDBService.getUsers()) {
			users.add(user);
		}
		return users;
	}
}
