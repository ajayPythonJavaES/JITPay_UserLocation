package com.jitpay.user.location.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jitpay.user.location.model.Location;
import com.jitpay.user.location.model.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, String>{

	@Query("SELECT ul.location FROM UserLocation ul where ul.userId=?1 and ul.createdOn between ?2 and ?3")
	List<Location> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
	
}
