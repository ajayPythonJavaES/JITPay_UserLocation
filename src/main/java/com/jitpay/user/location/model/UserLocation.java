package com.jitpay.user.location.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_LOCATION")
public class UserLocation {

	@Id
	private String userLocationId;

	@OneToOne(cascade=CascadeType.ALL, targetEntity=User.class)
	@JoinColumn(name="userId")
	private Set<User> userId = new LinkedHashSet<>();

	@OneToMany(cascade=CascadeType.ALL, targetEntity=Location.class)
	@JoinColumn(name="locationId")
	private Set<Location> locationId = new LinkedHashSet<>();
	
	private String errorMessage;
	
	@Column
	private String createdOn;

	public String getUserLocationId() {
		return userLocationId;
	}

	public void setUserLocationId(String userLocationId) {
		this.userLocationId = userLocationId;
	}

	public Set<User> getUserId() {
		return userId;
	}

	public void setUserId(Set<User> userId) {
		this.userId = userId;
	}

	public Set<Location> getLocationId() {
		return locationId;
	}

	public void setLocationId(Set<Location> locationId) {
		this.locationId = locationId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
