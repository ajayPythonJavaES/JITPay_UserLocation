package com.jitpay.user.location.model.request;

import com.jitpay.user.location.entity.Location;

public class UserLocationFromMobileAppRequest {

	private String userId;
	private String createdOn;
	private Location location;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
