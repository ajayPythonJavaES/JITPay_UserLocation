package com.jitpay.user.location.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitpay.user.location.model.User;
import com.jitpay.user.location.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public void saveOrUpdateUser(User user) throws Exception {
		Optional<User> existingUser = userRepository.findById(user.getUserId());
		if (!existingUser.isEmpty()) {
			User availableUser = existingUser.get();
			if (user.equals(availableUser))
				throw new Exception("User already exists");
			availableUser.setFirstName(user.getFirstName());
			availableUser.setSecondName(user.getSecondName());
			availableUser.setEmail(user.getEmail());
			availableUser.setCreatedOn(user.getCreatedOn());

			userRepository.save(availableUser);
		} else {
			userRepository.save(user);
		}
	}

	public Optional<User> getUserById(String userId) {
		return userRepository.findById(userId);
	}
	
}
