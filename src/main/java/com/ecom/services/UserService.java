package com.ecom.services;

import com.ecom.entities.User;

public interface UserService {

    public User saveUser(User user);

    public User getUserByEmail(String email);

}
