package com.jitpay.user.location.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jitpay.user.location.entity.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, String> {

	@Query("SELECT ul FROM UserLocation ul where ul.userId=?1 and ul.createdOn > ?2 and ul.createdOn <= ?3")
	List<UserLocation> getUserLocationsForADateRange(String userId, LocalDateTime fromDateTime,
			LocalDateTime toDateTime);

	@Query("SELECT ul FROM UserLocation ul where ul.userId=?1")
	List<UserLocation> getUserLocationsByUserId(String userId);
	
	@Query("SELECT ul FROM UserLocation ul where ul.userId=?1 and ul.createdOn = (SELECT max(createdOn) FROM UserLocation where userId = ?1)")
	List<UserLocation> findByUserIdUserLocations(String userId, Sort sort);
	
}
