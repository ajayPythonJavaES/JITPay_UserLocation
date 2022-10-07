package com.jitpay.user.location.controller;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jitpay.user.location.exception.JITPayExceptionHandler;
import com.jitpay.user.location.model.User;
import com.jitpay.user.location.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save_update_user")
	public @ResponseBody ResponseEntity<String> saveOrUpdateUser(@RequestBody User user) {

		if (user != null) {
			try {
				userService.saveOrUpdateUser(user);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
		}

		return new ResponseEntity<String>("User created, userId: " + user.getUserId(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/user_details/{userId}")
	public @ResponseBody ResponseEntity<Object> getUserDetailsByUserId(@PathVariable String userId) {
		Optional<User> user = null;
		try {
			user = userService.getUserById(userId);
			if (user.isEmpty())
				throw new EntityNotFoundException("User does not exist with user id: " + userId);
		} catch (EntityNotFoundException e) {
			return new JITPayExceptionHandler().handleEntityNotFound(e);
		}

		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

}
