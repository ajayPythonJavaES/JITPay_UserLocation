package com.jitpay.user.location.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jitpay.user.location.entity.User;
import com.jitpay.user.location.entity.UserLocation;
import com.jitpay.user.location.model.response.UserLatestLocationResponse;
import com.jitpay.user.location.repository.UserLocationRepository;

@Service
public class UserLocationServiceImpl implements UserLocationService{

	@Autowired
	private UserLocationRepository userLocationRepository;
	
	@Autowired
	private UserService userService;
	
	public boolean saveUserLocation(UserLocation userLocation) {
		if(userService.getUserById(userLocation.getUserId()).isEmpty())
			return false;
		userLocationRepository.save(userLocation);
		return true;
	}
	
	public List<UserLocation> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		return userLocationRepository.getUserLocationsForADateRange(userId, fromDateTime, toDateTime);
	}
	
	public UserLatestLocationResponse getLatestLocationOfUser(User user) {
		UserLatestLocationResponse userLatestLocationResponse = null;
		List<UserLocation> userLocations = userLocationRepository.findByUserIdUserLocations(user.getUserId(), Sort.by(Sort.Direction.DESC, "createdOn"));
		if(Objects.nonNull(userLocations) && userLocations.size() > 0) {
			userLatestLocationResponse = new UserLatestLocationResponse();
			userLatestLocationResponse.setUserId(user.getUserId());
			userLatestLocationResponse.setCreatedOn(userLocations.get(0).getCreatedOn().toString());
			userLatestLocationResponse.setEmail(user.getEmail());
			userLatestLocationResponse.setFirstName(user.getFirstName());
			userLatestLocationResponse.setSecondName(user.getSecondName());
			userLatestLocationResponse.setLocation(userLocations.get(0).getLocation());
		}
		return userLatestLocationResponse;
	}
	
}
