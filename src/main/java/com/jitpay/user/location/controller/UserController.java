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

import com.jitpay.user.location.entity.User;
import com.jitpay.user.location.exception.JITPayExceptionHandler;
import com.jitpay.user.location.service.UserService;

/**
 * @author Ajay Sarvasiddhi
 * This is a  rest controller to manage the user activities, like, save or update a user and getting the user details with the help of user id. *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public @ResponseBody ResponseEntity<String> saveOrUpdateUser(@RequestBody User user) {
		try {
			userService.saveOrUpdateUser(user);		//Service call to perform the transaction
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("User created, userId: " + user.getUserId(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/user/{userId}")
	public @ResponseBody ResponseEntity<Object> getUserDetailsByUserId(@PathVariable String userId) {
		Optional<User> user = Optional.ofNullable(getUser(userId));
		try {
			if (user.isEmpty())
				throw new EntityNotFoundException("User does not exist with user id: " + userId);
		} catch (EntityNotFoundException e) {
			return new JITPayExceptionHandler().handleEntityNotFound(e);
		}

		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

	private User getUser(String userId) {
		if (userService.getUserById(userId).isPresent()) {
			return userService.getUserById(userId).get();
		}
		return null;
	}
}
