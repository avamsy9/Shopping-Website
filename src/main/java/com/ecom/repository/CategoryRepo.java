package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

    public Boolean existsByName(String name);
    
}
