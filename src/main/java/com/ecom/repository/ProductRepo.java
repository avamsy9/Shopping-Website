package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

}
