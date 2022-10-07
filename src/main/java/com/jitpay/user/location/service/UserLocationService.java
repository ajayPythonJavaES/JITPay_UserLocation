package com.jitpay.user.location.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jitpay.user.location.model.request.UserLocationRequest;

public interface UserLocationService {

	public void saveUserLocation(UserLocationRequest userLocation);
	
	public List<UserLocationRequest> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
	
}
