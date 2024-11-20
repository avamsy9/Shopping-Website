package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entities.User;
import com.ecom.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepo.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUser(user);

    }



}
