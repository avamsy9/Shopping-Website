package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.ProductOrder;

public interface ProductOrderRepo extends JpaRepository<ProductOrder,Integer>{

    

}
