package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

    public List<User> findByRole(String role);

}
