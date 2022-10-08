package com.jitpay.user.location.model.response;

import com.jitpay.user.location.entity.Location;

public class LocationsResponse {

	private String createdOn;
	private Location location;

	public LocationsResponse(String createdOn, Location location) {
		this.createdOn = createdOn;
		this.location = location;
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
