package com.jitpay.user.location.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jitpay.user.location.model.request.UserLocationRequest;

public interface UserLocationRepository extends JpaRepository<UserLocationRequest, String>{

	@Query("SELECT ul FROM UserLocationRequest ul where ul.userId=?1 and ul.createdOn > ?2 and ul.createdOn <= ?3")
	List<UserLocationRequest> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime);	
	
}
