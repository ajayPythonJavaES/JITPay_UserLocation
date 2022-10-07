package com.jitpay.user.location.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jitpay.user.location.model.Location;
import com.jitpay.user.location.model.UserLocation;

public interface UserLocationService {

	public void saveUserLocation(UserLocation userLocation);
	
	public List<Location> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
	
}
