package com.jitpay.user.location.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.jitpay.user.location.entity.UserLocation;
import com.jitpay.user.location.model.response.LocationsResponse;
import com.jitpay.user.location.model.response.UserLocationResponse;

public class JITPayUserAPIUtil {

	public static LocalDateTime convertStringToLocalDateTime(String dateTime) throws DateTimeParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, formatter);
		return parsedDateTime;
	}
	
	public static UserLocationResponse prepareResponse(String userId, List<UserLocation> userLocations) {
		UserLocationResponse userLocationResponse = new UserLocationResponse();
		userLocationResponse.setUserId(userId);
		userLocationResponse.setLocations(userLocations.stream()
				.map((userLocation) -> new LocationsResponse(userLocation.getCreatedOn().toString(),
						userLocation.getLocation()))
				.collect(Collectors.toList()));

		return userLocationResponse;
	}
	
}
