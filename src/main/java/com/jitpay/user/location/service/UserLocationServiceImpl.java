package com.jitpay.user.location.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitpay.user.location.model.Location;
import com.jitpay.user.location.model.UserLocation;
import com.jitpay.user.location.repository.UserLocationRepository;

@Service
public class UserLocationServiceImpl implements UserLocationService{

	@Autowired
	private UserLocationRepository userLocationRepository;
	
	public void saveUserLocation(UserLocation userLocation) {
		userLocationRepository.save(userLocation);
	}
	
	public List<Location> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		return userLocationRepository.getUserLocationsForADateRange(userId, fromDateTime, toDateTime);
	}
	
	
}
