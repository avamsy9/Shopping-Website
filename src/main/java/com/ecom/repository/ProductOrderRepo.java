package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.ProductOrder;

public interface ProductOrderRepo extends JpaRepository<ProductOrder,Integer>{
    
    List<ProductOrder> findByUserId(Integer userId);

}
