package com.jitpay.user.location.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jitpay.user.location.exception.JITPayExceptionHandler;
import com.jitpay.user.location.model.UserLocation;
import com.jitpay.user.location.service.UserLocationService;
import com.jitpay.user.location.service.UserService;

@RestController
public class UserLocationController {

	@Autowired
	private UserLocationService userLocationService;

	@Autowired
	private UserService userService;
	
	@PostMapping("/save_user_location")
	public @ResponseBody ResponseEntity<Object> saveUserLocation(@RequestBody UserLocation userLocation) {
		if(userLocation != null) {
			try {
				if(userService.getUserById(userLocation.getUserId()).isEmpty())
					throw new EntityNotFoundException("User does not exist with user id: " +userLocation.getUserId());
				else {
					userLocationService.saveUserLocation(userLocation);
				}
			} catch(EntityNotFoundException exc) {
				return new JITPayExceptionHandler().handleEntityNotFound(exc);
			}
				
		}
		
		return new ResponseEntity<Object>(new String("user location updated"), HttpStatus.OK);
	}
	
}
