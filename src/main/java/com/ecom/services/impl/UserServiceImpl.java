package com.ecom.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.entities.User;
import com.ecom.repository.UserRepo;
import com.ecom.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setRole("ROLE_USER");
        user.setIsEnable(true);
        
        String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
        return userRepo.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
	public List<User> getUsers(String role) {
		return userRepo.findByRole(role);
	}

    @Override
	public Boolean updateAccountStatus(Integer id, Boolean status) {
		Optional<User> findByuser = userRepo.findById(id);
		if (findByuser.isPresent()) {
			User user = findByuser.get();
			user.setIsEnable(status);
			userRepo.save(user);
			return true;
		}
		return false;
	}

}
