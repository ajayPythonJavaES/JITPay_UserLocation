package com.jitpay.user.location.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER_LOCATION")
public class UserLocation {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userLocationId;

	@Column
	private String userId;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	//@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;

	@OneToOne(targetEntity = Location.class, cascade = CascadeType.ALL)
	private Location location;

	public UserLocation(int userLocationId, String userId, LocalDateTime createdOn, Location location) {
		this.userLocationId = userLocationId;
		this.userId = userId;
		this.createdOn = createdOn;
		this.location = location;
	}
	
	
	public int getUserLocationId() {
		return userLocationId;
	}

	public void setUserLocationId(int userLocationId) {
		this.userLocationId = userLocationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof UserLocation))
			return false;
		UserLocation userLocation = (UserLocation) o;
		return userLocation.getUserId() == this.getUserId() && userLocation.getCreatedOn() == this.getCreatedOn()
				&& userLocation.getLocation().getLatitude() == this.getLocation().getLatitude()
				&& userLocation.getLocation().getLongitude() == this.getLocation().getLongitude();
	}
	
}
