package com.jitpay.user.location.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jitpay.user.location.model.User;
import com.jitpay.user.location.model.UserLocation;
import com.jitpay.user.location.model.UserLocationInputFromMobileApp;
import com.jitpay.user.location.service.UserLocationService;
import com.jitpay.user.location.service.UserService;

@RestController
public class UserLocationController {

	@Autowired
	private UserLocationService userLocationService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/user_location")
	public ResponseEntity<UserLocation> getUserLocation(@RequestBody UserLocationInputFromMobileApp userLocationInputFromMobileApp) {
		UserLocation userLocation = new UserLocation();
		if(userLocationInputFromMobileApp != null) {
			try {
				Optional<User> userExists = userService.getUserById(userLocationInputFromMobileApp.getUserId());
				if(userExists.isEmpty()) {
					throw new Exception("User does not exist with the user id: " + userLocationInputFromMobileApp.getUserId());
				} else {
					
				}
			} catch(Exception e) {
				userLocation.setErrorMessage(e.getMessage());
				return new ResponseEntity<UserLocation>(userLocation, HttpStatus.OK);
			}
		}
		
		return null;
	}
	
}
