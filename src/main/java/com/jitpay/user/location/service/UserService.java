package com.jitpay.user.location.service;

import java.util.Optional;

import com.jitpay.user.location.model.User;

public interface UserService {

	public void saveOrUpdateUser(User user) throws Exception;

	public Optional<User> getUserById(String userId);
}
