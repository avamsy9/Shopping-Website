package com.ecom.services;

import java.util.List;

import com.ecom.entities.User;

public interface UserService {

    public User saveUser(User user);

    public User getUserByEmail(String email);

    public List<User> getUsers(String role);

    public Boolean updateAccountStatus(Integer id, Boolean status);

}
