package com.jitpay.user.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	private String userId;

	@Column
	private String createdOn;

	@Column
	private String email;

	@Column
	private String firstName;

	@Column
	private String secondName;

	public User() {
		
	}
	
	public User(String userId, String firstName, String secondName, String email, String createdOn) {
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.createdOn = createdOn;
		this.email = email;
	}
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return user.getUserId().equals(this.getUserId()) && user.getFirstName().equals(this.getFirstName())
				&& user.getSecondName().equals(this.getSecondName()) && user.getEmail().equals(this.getEmail())
				&& user.getCreatedOn().equals(this.getCreatedOn());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + email.length() + firstName.length() + secondName.length() + createdOn.length();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		return result;
	}

}
