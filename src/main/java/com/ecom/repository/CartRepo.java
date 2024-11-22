package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    public Cart findByProductIdAndUserId(Integer productId, Integer userId);

    public Integer countByUserId(Integer userId);
    
	public List<Cart> findByUserId(Integer userId);
}
