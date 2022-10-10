package com.jitpay.user.location.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jitpay.user.location.entity.User;
import com.jitpay.user.location.entity.UserLocation;
import com.jitpay.user.location.exception.JITPayExceptionHandler;
import com.jitpay.user.location.service.UserLocationService;
import com.jitpay.user.location.service.UserService;
import com.jitpay.user.location.util.JITPayUserAPIUtil;

@RestController
public class UserLocationController {

	@Autowired
	private UserLocationService userLocationService;

	@Autowired
	private UserService userService;

	@PostMapping("/user/location")
	public @ResponseBody ResponseEntity<Object> saveUserLocation(@RequestBody UserLocation userLocation) {
		if (Objects.nonNull(userLocation)) {
			try {
				if (!userLocationService.saveUserLocation(userLocation))
					throw new EntityNotFoundException(
							"Problem occured in saving the user location, user not found with id: "
									+ userLocation.getUserId());
			} catch (EntityNotFoundException exc) {
				return new JITPayExceptionHandler().handleEntityNotFound(exc);
			}
		}
		return new ResponseEntity<Object>(new String("Location updated for user: " + userLocation.getUserId()),
				HttpStatus.OK);
	}

	@GetMapping("/user/location/{userId}")
	public @ResponseBody ResponseEntity<Object> getUserLocationsWithinDateRange(@PathVariable String userId,
			@RequestParam(name = "from") String fromDateTime, @RequestParam(name = "to") String toDateTime) {

		LocalDateTime fromDateT, toDateT;
		try {
			fromDateT = JITPayUserAPIUtil.convertStringToLocalDateTime(fromDateTime);
			toDateT = JITPayUserAPIUtil.convertStringToLocalDateTime(toDateTime);
			if (userService.getUserById(userId).isEmpty())
				throw new EntityNotFoundException("User does not exist with user id: " + userId);
		} catch (DateTimeParseException e) {
			return new JITPayExceptionHandler().handleParseException(e);
		} catch (EntityNotFoundException e) {
			return new JITPayExceptionHandler().handleEntityNotFound(e);
		}
		List<UserLocation> getUserLocations = userLocationService.getUserLocationsForADateRange(userId, fromDateT,
				toDateT);
		return new ResponseEntity<>(JITPayUserAPIUtil.prepareResponse(userId, getUserLocations), HttpStatus.OK);
	}

	@GetMapping("/user/latest/location/{userId}")
	public @ResponseBody ResponseEntity<Object> getLatestLocationOfTheUser(@PathVariable String userId) {
		User user = getUser(userId);
		try {
			if (Objects.isNull(user))
				throw new EntityNotFoundException("User does not exist with id: " + userId);
		} catch (EntityNotFoundException e) {
			return new JITPayExceptionHandler().handleEntityNotFound(e);
		}
		return new ResponseEntity<>(userLocationService.getLatestLocationOfUser(user), HttpStatus.OK);
	}

	private User getUser(String userId) {
		if (userService.getUserById(userId).isPresent()) {
			return userService.getUserById(userId).get();
		}
		return null;
	}

}
