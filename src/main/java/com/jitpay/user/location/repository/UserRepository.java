package com.jitpay.user.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitpay.user.location.entity.User;

public interface UserRepository extends JpaRepository<User, String> {


}
