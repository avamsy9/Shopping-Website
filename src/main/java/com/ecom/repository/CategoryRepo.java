package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

    public Boolean existsByName(String name);

    public List<Category> findByIsActiveTrue();
    
}
