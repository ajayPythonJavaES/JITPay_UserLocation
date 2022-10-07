package com.jitpay.user.location.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

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
import com.jitpay.user.location.model.request.UserLocationRequest;
import com.jitpay.user.location.model.response.LocationsResponse;
import com.jitpay.user.location.model.response.UserLocationResponse;
import com.jitpay.user.location.service.UserLocationService;
import com.jitpay.user.location.service.UserService;
import com.jitpay.user.location.util.JITPayUserAPIUtil;

@RestController
public class UserLocationController {

	@Autowired
	private UserLocationService userLocationService;

	@Autowired
	private UserService userService;

	@PostMapping("/save_user_location")
	public @ResponseBody ResponseEntity<Object> saveUserLocation(@RequestBody UserLocationRequest userLocation) {
		if (userLocation != null) {
			try {
				if (userService.getUserById(userLocation.getUserId()).isEmpty())
					throw new EntityNotFoundException("User does not exist with user id: " + userLocation.getUserId());
				else {
					userLocationService.saveUserLocation(userLocation);
				}
			} catch (EntityNotFoundException exc) {
				return new JITPayExceptionHandler().handleEntityNotFound(exc);
			}

		}

		return new ResponseEntity<Object>(new String("Location updated for user: " + userLocation.getUserId()),
				HttpStatus.OK);
	}

	@GetMapping("/get_user_locations_for_date_range/{userId}/{fromDateTime}/{toDateTime}")
	public @ResponseBody ResponseEntity<Object> getUserLocationsWithinDateRange(@PathVariable String userId,
			@PathVariable String fromDateTime, @PathVariable String toDateTime) {

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

		List<UserLocationRequest> getUserLocations = userLocationService.getUserLocationsForADateRange(userId,
				fromDateT, toDateT);
		System.out.println(getUserLocations);

		UserLocationResponse userLocationResponse = new UserLocationResponse();
		userLocationResponse.setUserId(userId);
		userLocationResponse.setLocations(getUserLocations.stream()
				.map((userLocation) -> new LocationsResponse(userLocation.getCreatedOn().toString(),
						userLocation.getLocation()))
				.collect(Collectors.toList()));

		// System.out.println("From date: " + fromDateT.toString());

		return new ResponseEntity<>(userLocationResponse, HttpStatus.OK);
	}

}
