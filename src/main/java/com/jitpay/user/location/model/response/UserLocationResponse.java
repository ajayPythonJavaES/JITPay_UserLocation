package com.jitpay.user.location.model.response;

import java.util.List;

public class UserLocationResponse {

	private String userId;

	private List<LocationsResponse> locations;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<LocationsResponse> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationsResponse> locations) {
		this.locations = locations;
	}

}
