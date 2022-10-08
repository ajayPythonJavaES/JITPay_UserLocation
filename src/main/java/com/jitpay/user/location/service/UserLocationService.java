package com.jitpay.user.location.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jitpay.user.location.entity.User;
import com.jitpay.user.location.entity.UserLocation;
import com.jitpay.user.location.model.response.UserLatestLocationResponse;

public interface UserLocationService {

	public boolean saveUserLocation(UserLocation userLocation);
	
	public List<UserLocation> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
	
	public UserLatestLocationResponse getLatestLocationOfUser(User user);
	
}
